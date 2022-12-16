package com.example.coolsports.presentation.teamStandings

import com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup.ScoreItem

interface OnGroupItemsClickListener {
    fun onClickListener(item: ScoreItem)
}