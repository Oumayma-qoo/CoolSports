package com.example.coolsports.presentation.league

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coolsports.common.utils.CustomBindingAdapters.loadImage

import com.example.coolsports.databinding.ItemLeagueBinding
import com.example.coolsports.domain.model.league.LeagueModel

class LeagueListAdapter(): ListAdapter<LeagueModel, LeagueListAdapter.LeagueViewHolder>(Companion) {


    interface OnItemTap {
        fun onTap(position: Int)
    }

    private var onTapListener: OnItemTap? = null
    private var context: Context? = null

    fun setItemTapListener(l: OnItemTap) {
        onTapListener = l
    }

    open inner class LeagueViewHolder(val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<LeagueModel>() {
        override fun areItemsTheSame(
            oldItem:LeagueModel,
            newItem:LeagueModel

        ): Boolean =
            oldItem.leagueId == newItem.leagueId

        override fun areContentsTheSame(
            oldItem: LeagueModel,
            newItem:LeagueModel
        ): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLeagueBinding.inflate(layoutInflater, parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.item= currentItem
        holder.binding.position= position
        holder.binding.clickListener = onTapListener
        holder.binding.executePendingBindings()
        loadImage(holder.binding.leagueImageView, currentItem.logo)

    }

    fun getLeagueId(position: Int): Int {
        val currentItem = getItem(position)
        return currentItem.leagueId!!
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

}

