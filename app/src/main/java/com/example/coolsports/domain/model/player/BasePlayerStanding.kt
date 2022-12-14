package com.example.coolsports.domain.model.player

import com.google.gson.annotations.SerializedName

data class BasePlayerStanding(
    @SerializedName("list")
    var list: List<Player> = listOf(),

    )