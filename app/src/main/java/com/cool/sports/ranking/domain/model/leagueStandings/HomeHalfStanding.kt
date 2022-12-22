package com.cool.sports.ranking.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeHalfStanding(
    @SerializedName("drawCount")
    var drawCount: Int = 0,
    @SerializedName("drawRate")
    var drawRate: String = "",
    @SerializedName("getScore")
    var getScore: Int = 0,
    @SerializedName("goalDifference")
    var goalDifference: Int = 0,
    @SerializedName("integral")
    var integral: Int = 0,
    @SerializedName("loseAverage")
    var loseAverage: Double = 0.0,
    @SerializedName("loseCount")
    var loseCount: Int = 0,
    @SerializedName("loseRate")
    var loseRate: String = "",
    @SerializedName("loseScore")
    var loseScore: Int = 0,
    @SerializedName("rank")
    var rank: Int = 0,
    @SerializedName("teamId")
    var teamId: Int = 0,
    @SerializedName("totalCount")
    var totalCount: Int = 0,
    @SerializedName("winAverage")
    var winAverage: Double = 0.0,
    @SerializedName("winCount")
    var winCount: Int = 0,
    @SerializedName("winRate")
    var winRate: String = ""
):Parcelable