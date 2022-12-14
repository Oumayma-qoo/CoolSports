package com.example.coolsports.domain.model.team

import com.google.gson.annotations.SerializedName

data class BaseTeam(
    @SerializedName("teamInfoData")
    var teamInfoData: List<TeamInfo> = listOf(),
    @SerializedName("teamPlayerData")
    var teamPlayerData: List<TeamPlayer> = listOf(),
    @SerializedName("teamPlayerTransferData")
    var teamPlayerTransferData: List<Any> = listOf(),
)