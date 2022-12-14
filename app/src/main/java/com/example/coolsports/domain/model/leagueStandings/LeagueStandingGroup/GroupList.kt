package com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup

import com.google.gson.annotations.SerializedName

data class GroupList(
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("season")
    var season: String = "",
    @SerializedName("score")
    var score: List<Score> = listOf()

)