package com.example.coolsports.presentation.teamStandings

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.presentation.playerStandings.PlayerStandingsFragment

class ViewPagerAdapter(val fragment: Fragment,val rules: LeagueData04) : FragmentStateAdapter(fragment){
    private val NUM_ITEMS = 2
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TeamStandingsFragment(rules)
            1 -> PlayerStandingsFragment()
            else -> TeamStandingsFragment(rules)
        }
    }


}