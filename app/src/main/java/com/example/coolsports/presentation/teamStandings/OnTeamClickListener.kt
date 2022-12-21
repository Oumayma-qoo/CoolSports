package com.example.coolsports.presentation.teamStandings

import com.example.coolsports.domain.model.leagueStandings.TotalStanding
import com.example.coolsports.domain.model.leagueStandings.TotalStandingWithTeamInfo

interface OnTeamClickListener {
    fun onClickListener(totalStanding: TotalStandingWithTeamInfo)
}