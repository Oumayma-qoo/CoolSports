package com.example.coolsports.presentation.playerStandings

import com.example.coolsports.domain.model.player.Player

interface OnPlayerClickListener {
    fun onClickListener(player: Player)
}