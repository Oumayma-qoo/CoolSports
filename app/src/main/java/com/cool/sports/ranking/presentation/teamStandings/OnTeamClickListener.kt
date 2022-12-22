package com.cool.sports.ranking.presentation.teamStandings

import com.cool.sports.ranking.domain.model.leagueStandings.TotalStanding
import com.cool.sports.ranking.domain.model.leagueStandings.TotalStandingWithTeamInfo

interface OnTeamClickListener {
    fun onClickListener(totalStanding: TotalStandingWithTeamInfo)
}