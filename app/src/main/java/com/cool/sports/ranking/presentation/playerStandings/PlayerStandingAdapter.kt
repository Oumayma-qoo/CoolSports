package com.cool.sports.ranking.presentation.playerStandings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.cool.sports.ranking.common.constant.Constants
import com.cool.sports.ranking.common.sharedPreference.SPApp
import com.cool.sports.ranking.databinding.ItemPlayerBinding
import com.cool.sports.ranking.domain.model.leagueStandings.TotalStandingWithTeamInfo
import com.cool.sports.ranking.domain.model.player.Player

class PlayerStandingAdapter(
    private val context: Context,
    private val listener: OnPlayerClickListener,
    private var playersList: List<Player>,
) : RecyclerView.Adapter<PlayerStandingAdapter.PlayerHolder>() , Filterable {
    private var dataFiltered = mutableListOf<Player>()
    private var data = mutableListOf<Player>()
    private lateinit var sp: SPApp
    init {
        data.clear()
        data.addAll(playersList)
        dataFiltered.clear()
        dataFiltered.addAll(playersList)
        sp = SPApp(context)
    }

    inner class PlayerHolder(binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemPlayerBinding

        init {
            this.binding = binding
        }

        fun bindTo(playerItem: Player, context: Context, listener: OnPlayerClickListener) {

            binding.num.text = "${adapterPosition+1}"
            binding.player.text = playerItem.playerNameEn
            binding.teamName.text = playerItem.teamNameEn
            binding.goals.text = playerItem.goals.toString()
            binding.homeScore.text = playerItem.homeGoals.toString()
            binding.awayScore.text = playerItem.awayGoals.toString()
            if (sp.language == Constants.SharedPreferenceKeys.CHINESE) {
                binding.player.text = playerItem.playerNameChs
                binding.teamName.text = playerItem.teamNameChs
            }
            binding.root.setOnClickListener {
                listener.onClickListener(playerItem)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val binding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindTo(dataFiltered[position], context, listener)
    }


    override fun getItemCount(): Int {
        return dataFiltered.size
    }

    override fun getFilter(): Filter {
        return object :  Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) dataFiltered = data else {
                    val filteredList = mutableListOf<Player>()
                    data
                        .filter {
                            (it.playerNameEn!!.contains(constraint!!,true))or
                                    (it.teamNameEn!!.contains(constraint,true))

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
                    results.values as MutableList<Player>
                notifyDataSetChanged()
            }

        }
    }


}