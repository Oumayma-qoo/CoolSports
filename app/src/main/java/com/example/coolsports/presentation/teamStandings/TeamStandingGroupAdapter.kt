package com.example.coolsports.presentation.teamStandings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.coolsports.common.constant.Constants
import com.example.coolsports.common.sharedPreference.SPApp
import com.example.coolsports.databinding.ItemGroupBinding
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.GroupScore
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.ScoreItem
import com.example.coolsports.domain.model.leagueStandings.TotalStandingWithTeamInfo
import com.example.coolsports.domain.model.team.TeamInfo

class TeamStandingGroupAdapter(
    private val context: Context,
    private val listener: OnGroupItemsClickListener,
    var groupScoreList:List<GroupScore>
) : RecyclerView.Adapter<TeamStandingGroupAdapter.TeamStandingGroupHolder>() , Filterable {
    private var dataFiltered = mutableListOf<GroupScore>()
    private var data = mutableListOf<GroupScore>()
    private lateinit var sp: SPApp

    init {
        data.clear()
        data.addAll(groupScoreList)
        dataFiltered.clear()
        dataFiltered.addAll(groupScoreList)
        sp = SPApp(context)

    }

    inner class TeamStandingGroupHolder(binding: ItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemGroupBinding

        init {
            this.binding = binding
        }

        fun bindTo(groupScore: GroupScore, context: Context, listener: OnGroupItemsClickListener) {

            // TODO: handle language
            binding.team.text = groupScore.groupEn
            if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                binding.team.text = groupScore.groupCn
            }
            binding.teamRecyclerView.adapter = ScoreItemsAdapter(context,listener,groupScore.scoreItems)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamStandingGroupHolder {
       val binding= ItemGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TeamStandingGroupHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamStandingGroupHolder, position: Int) {
       holder.bindTo(dataFiltered[position],context,listener)
    }

    override fun getItemCount(): Int {
       return dataFiltered.size
    }

    override fun getFilter(): Filter {
        return object :  Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) dataFiltered = data else {
                    val filteredList = mutableListOf<GroupScore>()
                    data
                        .filter {
                            it.scoreItems
                                .filter { scoreItem ->
                                (scoreItem.teamNameEn.contains(constraint!!,true))

                            }
                                .isNotEmpty()

                        }
                        .forEach { filteredList.add(it) }
                    dataFiltered = filteredList

                }
                return FilterResults().apply { values = dataFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFiltered= if (results?.values == null)
                    mutableListOf()
                else
                    results.values as MutableList<GroupScore>
                notifyDataSetChanged()
            }

        }
    }

}