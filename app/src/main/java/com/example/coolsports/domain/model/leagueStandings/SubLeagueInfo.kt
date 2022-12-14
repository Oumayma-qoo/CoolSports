package com.example.coolsports.domain.model.leagueStandings

import com.google.gson.annotations.SerializedName


data class SubLeagueInfo(
    @SerializedName("countRound")
    var countRound: Int = 0,
    @SerializedName("currRound")
    var currRound: Int = 0,
    @SerializedName("hasStandings")
    var hasStandings: Boolean = false,
    @SerializedName("IsCurrentSclass")
    var isCurrentSclass: Boolean = false,
    @SerializedName("isPrimary")
    var isPrimary: Boolean = false,
    @SerializedName("isTwoRounds")
    var isTwoRounds: Boolean = false,
    @SerializedName("nameChs")
    var nameChs: String = "",
    @SerializedName("nameCht")
    var nameCht: String = "",
    @SerializedName("nameEn")
    var nameEn: String = "",
    @SerializedName("subId")
    var subId: Int = 0
)