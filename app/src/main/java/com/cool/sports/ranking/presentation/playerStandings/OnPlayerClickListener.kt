package com.cool.sports.ranking.presentation.playerStandings

import com.cool.sports.ranking.domain.model.player.Player

interface OnPlayerClickListener {
    fun onClickListener(player: Player)
}