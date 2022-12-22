package com.cool.sports.ranking.domain.model.leagueStandings

import android.os.Parcelable
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TotalStanding(
    @SerializedName("rank")
    var rank: Int? = null,
    @SerializedName("teamId")
    var teamId: Int? = null,
    @SerializedName("winRate")
    var winRate: String? = null,
    @SerializedName("drawRate")
    var drawRate: String? = null,
    @SerializedName("loseRate")
    var loseRate: String? = null,
    @SerializedName("winAverage")
    var winAverage: Double? = null,
    @SerializedName("loseAverage")
    var loseAverage: Double? = null,
    @SerializedName("deduction")
    var deduction: Double? = null,
    @SerializedName("deductionExplainCn")
    var deductionExplainCn: String? = null,
    @SerializedName("recentFirstResult")
    var recentFirstResult: String? = null,
    @SerializedName("recentSecondResult")
    var recentSecondResult: String? = null,
    @SerializedName("recentThirdResult")
    var recentThirdResult: String? = null,
    @SerializedName("recentFourthResult")
    var recentFourthResult: String? = null,
    @SerializedName("recentFifthResult")
    var recentFifthResult: String? = null,
    @SerializedName("recentSixthResult")
    var recentSixthResult: String? = null,
    @SerializedName("deductionExplainEn")
    var deductionExplainEn: String? = null,
    @SerializedName("color")
    var color: Int? = null,
    @SerializedName("redCard")
    var redCard: Int? = null,
    @SerializedName("totalCount")
    var totalCount: Int? = null,
    @SerializedName("winCount")
    var winCount: Int? = null,
    @SerializedName("drawCount")
    var drawCount: Int? = null,
    @SerializedName("loseCount")
    var loseCount: Int? = null,
    @SerializedName("getScore")
    var getScore: Int? = null,
    @SerializedName("loseScore")
    var loseScore: Int? = null,
    @SerializedName("goalDifference")
    var goalDifference: Int? = null,
    @SerializedName("integral")
    var integral: Int? = null,
    @SerializedName("totalAddScore")
    var totalAddScore: Int? = null
) : Parcelable

