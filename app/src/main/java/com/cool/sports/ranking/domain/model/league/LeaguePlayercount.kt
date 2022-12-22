package com.cool.sports.ranking.domain.model.league

import com.google.gson.annotations.SerializedName


data class LeaguePlayercount(
    @SerializedName("list")
    var list: Any? = Any()
)