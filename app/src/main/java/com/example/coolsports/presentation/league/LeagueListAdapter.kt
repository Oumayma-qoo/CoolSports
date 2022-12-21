package com.example.coolsports.presentation.league

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coolsports.common.utils.CustomBindingAdapters.loadImage

import com.example.coolsports.databinding.ItemLeagueBinding
import com.example.coolsports.domain.model.league.LeagueModel
import com.example.coolsports.domain.model.leagueStandings.TotalStandingWithTeamInfo
import java.util.*

class LeagueListAdapter(var data: MutableList<LeagueModel>): ListAdapter<LeagueModel, LeagueListAdapter.LeagueViewHolder>(Companion), Filterable {

    var dataFiltered= mutableListOf<LeagueModel>()
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

    interface OnItemTap {
        fun onTap(position: Int)
    }


    private var onTapListener: OnItemTap? = null
    private var context: Context? = null

    fun setItemTapListener(l: OnItemTap) {
        onTapListener = l
    }
   init{
        dataFiltered.clear()
        dataFiltered.addAll(data)
    }
    open inner class LeagueViewHolder(val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLeagueBinding.inflate(layoutInflater, parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        try {
            holder.binding.item= dataFiltered[position]
            holder.binding.position= position
            holder.binding.clickListener = onTapListener
            holder.binding.executePendingBindings()
            loadImage(holder.binding.leagueImageView,  dataFiltered[position].logo)
        }catch (e:Exception){

            Log.d("test===",e.message.toString())
        }


    }


    fun getLeagueId(position: Int): Int {
        val currentItem =dataFiltered[position]
        return currentItem.leagueId!!
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }



    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<LeagueModel>()
                val charString = constraint?.toString() ?: ""
                dataFiltered = if (charString.isEmpty()) data else {
                    dataFiltered.clear()
                    data
                        .filter {
                            (it.leagueName!!.contains(constraint!!,true))

                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = dataFiltered }

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFiltered= if (results?.values == null)
                    mutableListOf()
                else
                    results.values as MutableList<LeagueModel>
                notifyDataSetChanged()
            }

        }
    }

}

