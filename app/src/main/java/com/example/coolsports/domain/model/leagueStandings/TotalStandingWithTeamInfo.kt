package com.example.coolsports.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TotalStandingWithTeamInfo(
    var color: Int = 0,
    var deduction: Int = 0,
    var deductionExplainCn: String = "",
    var deductionExplainEn: String = "",
    var drawCount: Int = 0,
    var drawRate: String = "",
    var getScore: Int = 0,
    var goalDifference: Int = 0,
    var integral: Int = 0,
    var loseAverage: Double = 0.0,
    var loseCount: Int = 0,
    var loseRate: String = "",
    var loseScore: Int = 0,
    var rank: Int = 0,
    var recentFifthResult: String = "",
    var recentFirstResult: String = "",
    var recentFourthResult: String = "",
    var recentSecondResult: String = "",
    var recentSixthResult: String = "",
    var recentThirdResult: String = "",
    var redCard: Int = 0,
    var teamId: Int = 0,
    var totalAddScore: Int = 0,
    var totalCount: Int = 0,
    var winAverage: Double = 0.0,
    var winCount: Int = 0,
    var winRate: String = "",
    var conferenceFlg: Int = 0,
    var flag: String = "",
    var nameChs: String = "",
    var nameCht: String = "",
    var nameCn: String = "",
    var nameEn: String = ""

):Parcelable