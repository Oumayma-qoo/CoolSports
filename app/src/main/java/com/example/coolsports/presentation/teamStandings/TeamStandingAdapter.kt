package com.example.coolsports.presentation.teamStandings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.coolsports.databinding.ItemTeamStandingBinding
import com.example.coolsports.domain.model.leagueStandings.TotalStanding
import com.example.coolsports.domain.model.team.TeamInfo

class TeamStandingAdapter(
    private val context: Context,
    private val listener: OnTeamClickListener,
    var teams:ArrayList<TeamInfo>,
    var rankings:ArrayList<TotalStanding>
) : RecyclerView.Adapter<TeamStandingAdapter.TeamStandingHolder>(), Filterable {


    inner class TeamStandingHolder(binding: ItemTeamStandingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemTeamStandingBinding

        init {
            this.binding = binding
        }

        fun bindTo(totalStanding: TotalStanding, context: Context, listener: OnTeamClickListener) {

            binding.num.text = totalStanding.rank.toString()
            binding.p.text = totalStanding.totalCount.toString()
            binding.w.text = totalStanding.winCount.toString()
            binding.d.text = totalStanding.drawCount.toString()
            binding.l.text = totalStanding.loseCount.toString()
            binding.g.text = totalStanding.goalDifference.toString()
            binding.pts.text = totalStanding.integral.toString()
            //  binding.team.text =


//            Glide.with(context)
//                .load(totalStanding.homeLogo)
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
       holder.bindTo(rankings[position],context,listener)
    }

    override fun getItemCount(): Int {
       return rankings.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}