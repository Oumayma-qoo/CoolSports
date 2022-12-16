package com.example.coolsports.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueStandingsBase (
    @SerializedName("awayHalfStandings")
    var awayHalfStandings: List<AwayHalfStanding> = listOf(),
    @SerializedName("awayStandings")
    var awayStandings: List<AwayStanding> = listOf(),
    @SerializedName("halfStandings")
    var halfStandings: List<HalfStanding> = listOf(),
    @SerializedName("homeHalfStandings")
    var homeHalfStandings: List<HomeHalfStanding> = listOf(),
    @SerializedName("homeStandings")
    var homeStandings: List<HomeStanding> = listOf(),
    @SerializedName("isConference")
    var isConference: Boolean = false,
    @SerializedName("lastUpdateTime")
    var lastUpdateTime: String = "",
    @SerializedName("leagueColorInfos")
    var leagueColorInfos: List<LeagueColorInfo> = listOf(),
    @SerializedName("leagueInfo")
    var leagueInfo: LeagueInfo = LeagueInfo(),
    @SerializedName("scoreCountType")
    var scoreCountType: Int = 0,
    @SerializedName("subLeagueInfo")
    var subLeagueInfo: List<SubLeagueInfo> = listOf(),
    @SerializedName("teamInfo")
    var teamInfo: List<TeamInfos> = listOf(),
    @SerializedName("totalStandings")
    var totalStandings: List<TotalStanding> = listOf()
        ):Parcelable