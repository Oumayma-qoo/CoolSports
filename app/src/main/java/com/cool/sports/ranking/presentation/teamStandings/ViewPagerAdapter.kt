package com.cool.sports.ranking.presentation.teamStandings

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cool.sports.ranking.domain.model.league.LeagueData04
import com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingsBase
import com.cool.sports.ranking.presentation.league.LeagueViewModel
import com.cool.sports.ranking.presentation.playerStandings.PlayerStandingsFragment

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