package com.example.coolsports.domain.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize


@Parcelize
data class BaseLeagueInfo(
    @SerializedName("leagueData01")
    var leagueData01: List<LeagueData01> = listOf(),
    @SerializedName("leagueData02")
    var leagueData02: List<LeagueData02> = listOf(),
    @SerializedName("leagueData03")
    var leagueData03:  @RawValue List<Any> = listOf(),
    @SerializedName("leagueData04")
    var leagueData04: List<LeagueData04> = listOf(),
    @SerializedName("leaguePlayercount")
    var leaguePlayercount:  @RawValue LeaguePlayercount = LeaguePlayercount(),
    @SerializedName("leagueStanding")
    var leagueStanding:  @RawValue List<Any> = listOf(),
    @SerializedName("leagueTopscorer")
    var leagueTopscorer: @RawValue  List<Any> = listOf()
): Parcelable
