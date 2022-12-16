package com.example.coolsports.presentation.teamStandings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coolsports.databinding.ItemGroupBinding
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.GroupScore
import com.example.coolsports.domain.model.team.TeamInfo

class TeamStandingGroupAdapter(
    private val context: Context,
    private val listener: OnGroupItemsClickListener,
    var teams:ArrayList<TeamInfo>,
    var groupScoreList:ArrayList<GroupScore>
) : RecyclerView.Adapter<TeamStandingGroupAdapter.TeamStandingGroupHolder>() {


    inner class TeamStandingGroupHolder(binding: ItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemGroupBinding

        init {
            this.binding = binding
        }

        fun bindTo(groupScore: GroupScore, context: Context, listener: OnGroupItemsClickListener) {

            // TODO: handle language
            binding.team.text = groupScore.groupEn
            binding.teamRecyclerView.adapter = ScoreItemsAdapter(context,listener,groupScore.scoreItems)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamStandingGroupHolder {
       val binding= ItemGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TeamStandingGroupHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamStandingGroupHolder, position: Int) {
       holder.bindTo(groupScoreList[position],context,listener)
    }

    override fun getItemCount(): Int {
       return groupScoreList.size
    }

}