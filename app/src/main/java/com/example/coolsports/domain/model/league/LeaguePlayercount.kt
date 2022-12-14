package com.example.coolsports.domain.model.league

import com.google.gson.annotations.SerializedName

data class LeaguePlayercount(
    @SerializedName("list")
    var list: Any? = Any()
)