package com.example.coolsports.domain.model.league

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
    var leagueStanding: List<Any> = listOf(),
    @SerializedName("leagueTopscorer")
    var leagueTopscorer:  List<Any> = listOf()
)
