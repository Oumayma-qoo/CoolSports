package com.cool.sports.ranking.domain.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueData02(
    @SerializedName("currentRound")
    var currentRound: String = "",
    @SerializedName("currentSeason")
    var currentSeason: String = "",
    @SerializedName("hasScore")
    var hasScore: Boolean = false,
    @SerializedName("includeSeason")
    var includeSeason: String = "",
    @SerializedName("isCurrentSub")
    var isCurrentSub: Boolean = false,
    @SerializedName("isTwoRounds")
    var isTwoRounds: Boolean = false,
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("num")
    var num: String = "",
    @SerializedName("subId")
    var subId: Int = 0,
    @SerializedName("subNameChs")
    var subNameChs: String = "",
    @SerializedName("subNameCht")
    var subNameCht: String = "",
    @SerializedName("subNameEn")
    var subNameEn: String = "",
    @SerializedName("subNameId")
    var subNameId: String = "",
    @SerializedName("subNameTh")
    var subNameTh: String = "",
    @SerializedName("subNameVi")
    var subNameVi: String = "",
    @SerializedName("totalRound")
    var totalRound: String = ""
): Parcelable