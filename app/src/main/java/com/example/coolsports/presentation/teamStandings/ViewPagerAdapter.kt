package com.example.coolsports.presentation.teamStandings

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coolsports.domain.model.league.LeagueData04
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.example.coolsports.domain.model.leagueStandings.LeagueStandingsBase
import com.example.coolsports.presentation.league.LeagueViewModel
import com.example.coolsports.presentation.playerStandings.PlayerStandingsFragment

class ViewPagerAdapter(val fragment: Fragment,val rules: LeagueData04,  var leagueStanding: List<LeagueStandingsBase>, val leagueStanding2: List<LeagueStandingsGroupBase>, val leagueId: Int, val viewModel: LeagueViewModel) : FragmentStateAdapter(fragment){
    private val NUM_ITEMS = 2
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TeamStandingsFragment(rules,leagueStanding, leagueStanding2,leagueId,viewModel)
            1 -> PlayerStandingsFragment(leagueId,viewModel)
            else -> TeamStandingsFragment(rules,leagueStanding, leagueStanding2 ,leagueId,viewModel)
        }
    }


}