package com.example.coolsports.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TotalStanding(
    @SerializedName("color")
    var color: Int = 0,
    @SerializedName("deduction")
    var deduction: Int = 0,
    @SerializedName("deductionExplainCn")
    var deductionExplainCn: String = "",
    @SerializedName("deductionExplainEn")
    var deductionExplainEn: String = "",
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
    @SerializedName("recentFifthResult")
    var recentFifthResult: String = "",
    @SerializedName("recentFirstResult")
    var recentFirstResult: String = "",
    @SerializedName("recentFourthResult")
    var recentFourthResult: String = "",
    @SerializedName("recentSecondResult")
    var recentSecondResult: String = "",
    @SerializedName("recentSixthResult")
    var recentSixthResult: String = "",
    @SerializedName("recentThirdResult")
    var recentThirdResult: String = "",
    @SerializedName("redCard")
    var redCard: Int = 0,
    @SerializedName("teamId")
    var teamId: Int = 0,
    @SerializedName("totalAddScore")
    var totalAddScore: Int = 0,
    @SerializedName("totalCount")
    var totalCount: Int = 0,
    @SerializedName("winAverage")
    var winAverage: Double = 0.0,
    @SerializedName("winCount")
    var winCount: Int = 0,
    @SerializedName("winRate")
    var winRate: String = ""
):Parcelable