package com.example.coolsports.presentation.playerStandings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coolsports.databinding.ItemPlayerBinding
import com.example.coolsports.domain.model.player.Player

class PlayerStandingAdapter(
    private val context: Context,
    private val listener: OnPlayerClickListener,
    private var playersList: List<Player>
) : RecyclerView.Adapter<PlayerStandingAdapter.PlayerHolder>() {


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
        holder.bindTo(playersList[position], context, listener)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

}