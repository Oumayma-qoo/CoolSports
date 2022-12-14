package com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup

import com.google.gson.annotations.SerializedName

data class GroupScore(
    @SerializedName("groupCn")
    var groupCn: String = "",
    @SerializedName("groupEn")
    var groupEn: String = "",
    @SerializedName("scoreItems")
    var scoreItems: List<ScoreItem> = listOf()
)