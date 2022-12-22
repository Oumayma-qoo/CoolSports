package com.cool.sports.ranking.domain.model.league

import com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingGroup.LeagueStandingsGroupBase
import com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingsBase
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class BaseLeagueInfo(
    @SerializedName("leagueData01")
    var leagueData01: List<LeagueData01> = listOf(),
    @SerializedName("leagueData02")
    var leagueData02: List<LeagueData02> = listOf(),
    @SerializedName("leagueData03")
    var leagueData03: List<Any> = listOf(),
    @SerializedName("leagueData04")
    var leagueData04: List<LeagueData04> = listOf(),
    @SerializedName("leaguePlayercount")
    var leaguePlayercount: LeaguePlayercount = LeaguePlayercount(),
    @SerializedName("leagueStanding")
    var leagueStanding: List<LeagueStandingsBase> = listOf(),
    @Expose(serialize = false,deserialize = false)
    var leagueStanding2: List<LeagueStandingsGroupBase> = listOf(),
    @SerializedName("leagueTopscorer")
    var leagueTopscorer:  List<Any> = listOf()
)
