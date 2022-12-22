package com.cool.sports.ranking.presentation.teamStandings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cool.sports.ranking.common.constant.Constants
import com.cool.sports.ranking.common.sharedPreference.SPApp
import com.cool.sports.ranking.databinding.ItemTeamStandingBinding
import com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingGroup.ScoreItem

class ScoreItemsAdapter(
    private val context: Context,
    private val listener: OnGroupItemsClickListener,
    private var scoreItemList: List<ScoreItem>
) : RecyclerView.Adapter<ScoreItemsAdapter.TeamStandingHolder>() {

    private lateinit var sp: SPApp

    inner class TeamStandingHolder(binding: ItemTeamStandingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemTeamStandingBinding

        init {
            this.binding = binding
            sp = SPApp(context)

        }

        fun bindTo(scoreItem: ScoreItem, context: Context, listener: OnGroupItemsClickListener) {

            binding.num.text = scoreItem.rank
            binding.p.text = scoreItem.totalCount
            binding.w.text = scoreItem.winCount
            binding.d.text = scoreItem.drawCount
            binding.l.text = scoreItem.loseCount
            binding.g.text = scoreItem.goalDifference
            binding.pts.text = scoreItem.integral

            binding.team.text = scoreItem.teamNameEn
            if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                binding.team.text = scoreItem.teamNameChs
            }

            binding.root.setOnClickListener {
                listener.onClickListener(scoreItem)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamStandingHolder {
        val binding =
            ItemTeamStandingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamStandingHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamStandingHolder, position: Int) {
        holder.bindTo(scoreItemList[position], context, listener)
    }

    override fun getItemCount(): Int {
        return scoreItemList.size
    }

}