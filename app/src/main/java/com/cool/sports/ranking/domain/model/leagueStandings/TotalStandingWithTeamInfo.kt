package com.cool.sports.ranking.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TotalStandingWithTeamInfo(
    var rank: Int? = null,
    var teamId: Int? = null,
    var winRate: String? = null,
    var drawRate: String? = null,
    var loseRate: String? = null,
    var winAverage: Double? = null,
    var loseAverage: Double? = null,
    var deduction: Double? = null,
    var deductionExplainCn: String? = null,
    var recentFirstResult: String? = null,
    var recentSecondResult: String? = null,
    var recentThirdResult: String? = null,
    var recentFourthResult: String? = null,
    var recentFifthResult: String? = null,
    var recentSixthResult: String? = null,
    var deductionExplainEn: String? = null,
    var color: Int? = null,
    var redCard: Int? = null,
    var totalCount: Int? = null,
    var winCount: Int? = null,
    var drawCount: Int? = null,
    var loseCount: Int? = null,
    var getScore: Int? = null,
    var loseScore: Int? = null,
    var goalDifference: Int? = null,
    var integral: Int? = null,
    var totalAddScore: Int? = null,
    var conferenceFlg: Int = 0,
    var flag: String = "",
    var nameChs: String = "",
    var nameCht: String = "",
    var nameCn: String = "",
    var nameEn: String = ""
):Parcelable