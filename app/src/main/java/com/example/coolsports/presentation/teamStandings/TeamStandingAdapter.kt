package com.example.coolsports.presentation.teamStandings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coolsports.databinding.ItemTeamStandingBinding
import com.example.coolsports.domain.model.leagueStandings.LeagueInfo
import com.example.coolsports.domain.model.leagueStandings.TeamInfos
import com.example.coolsports.domain.model.leagueStandings.TotalStanding
import com.example.coolsports.domain.model.leagueStandings.TotalStandingWithTeamInfo
import com.example.coolsports.domain.model.team.TeamInfo

class TeamStandingAdapter(
    private val context: Context,
    private val listener: OnTeamClickListener,
    var rankings:List<TotalStandingWithTeamInfo>
) : RecyclerView.Adapter<TeamStandingAdapter.TeamStandingHolder>() , Filterable {
    private var dataFiltered = mutableListOf<TotalStandingWithTeamInfo>()
    private var data = mutableListOf<TotalStandingWithTeamInfo>()

    init {
        data.clear()
        data.addAll(rankings)
    }

    inner class TeamStandingHolder(binding: ItemTeamStandingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemTeamStandingBinding

        init {
            this.binding = binding
        }

        fun bindTo(totalStanding: TotalStandingWithTeamInfo, context: Context, listener: OnTeamClickListener) {

            binding.num.text = totalStanding.rank.toString()
            binding.p.text = totalStanding.totalCount.toString()
            binding.w.text = totalStanding.winCount.toString()
            binding.d.text = totalStanding.drawCount.toString()
            binding.l.text = totalStanding.loseCount.toString()
            binding.g.text = totalStanding.goalDifference.toString()
            binding.pts.text = totalStanding.integral.toString()

            binding.team.text = totalStanding.nameEn

//            Glide.with(context)
//                .load(teams.logo)
//                .into(binding.teamImageView)

            binding.root.setOnClickListener {
                listener.onClickListener(totalStanding)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamStandingHolder {
       val binding= ItemTeamStandingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TeamStandingHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamStandingHolder, position: Int) {
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
                   val filteredList = mutableListOf<TotalStandingWithTeamInfo>()
                   data
                       .filter {
                           (it.nameEn.contains(constraint!!,true))

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
                   results.values as MutableList<TotalStandingWithTeamInfo>
               notifyDataSetChanged()
           }

       }
    }


}