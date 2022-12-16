package com.example.coolsports.presentation.teamStandings

import com.example.coolsports.domain.model.leagueStandings.TotalStanding

interface OnTeamClickListener {
    fun onClickListener(totalStanding: TotalStanding)
}