package com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup

import com.google.gson.annotations.SerializedName


data class ScoreItem(
    @SerializedName("rank")
    var rank: String = "",
    @SerializedName("teamId")
    var teamId: String = "",
    @SerializedName("color")
    var color: String = "",
    @SerializedName("drawCount")
    var drawCount: String = "",
    @SerializedName("getScore")
    var getScore: String = "",
    @SerializedName("goalDifference")
    var goalDifference: String = "",
    @SerializedName("integral")
    var integral: String = "",
    @SerializedName("loseCount")
    var loseCount: String = "",
    @SerializedName("loseScore")
    var loseScore: String = "",
    @SerializedName("teamNameChs")
    var teamNameChs: String = "",
    @SerializedName("teamNameCht")
    var teamNameCht: String = "",
    @SerializedName("teamNameEn")
    var teamNameEn: String = "",
    @SerializedName("totalCount")
    var totalCount: String = "",
    @SerializedName("winCount")
    var winCount: String = ""
)