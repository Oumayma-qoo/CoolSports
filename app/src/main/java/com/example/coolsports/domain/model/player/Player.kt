package com.example.coolsports.domain.model.player

import com.example.coolsports.domain.mapper.NetworkModel
import com.google.gson.annotations.SerializedName

data class Player (
    @SerializedName("playerId")
    var playerId: Int? = null,
    @SerializedName("playerNameEn")
    var playerNameEn: String? = null,
    @SerializedName("playerNameChs")
    var playerNameChs: String? = null,
    @SerializedName("playerNameCht")
    var playerNameCht: String? = null,
    @SerializedName("countryEn")
    var countryEn: String? = null,
    @SerializedName("countryCn")
    var countryCn: String? = null,
    @SerializedName("teamID")
    var teamID: Int? = null,
    @SerializedName("teamNameEn")
    var teamNameEn: String? = null,
    @SerializedName("teamNameChs")
    var teamNameChs: String? = null,
    @SerializedName("teamNameCht")
    var teamNameCht: String? = null,
    @SerializedName("goals")
    var goals: Int? = null,
    @SerializedName("homeGoals")
    var homeGoals: Int? = null,
    @SerializedName("awayGoals")
    var awayGoals: Int? = null,
    @SerializedName("homePenalty")
    var homePenalty: Int? = null,
    @SerializedName("awayPenalty")
    var awayPenalty: Int? = null,

) : NetworkModel