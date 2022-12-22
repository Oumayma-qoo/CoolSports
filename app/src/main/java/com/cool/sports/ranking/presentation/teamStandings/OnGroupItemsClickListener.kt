package com.cool.sports.ranking.presentation.teamStandings

import com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingGroup.ScoreItem

interface OnGroupItemsClickListener {
    fun onClickListener(item: ScoreItem)
}