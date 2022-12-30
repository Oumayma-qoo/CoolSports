package com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingGroup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class GroupList(
    @SerializedName("subId")
    var subId: Int? = null,
    @SerializedName("isCurrent")
    var isCurrent: Boolean? = null,
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("season")
    var season: String = "",
    @SerializedName("score")
    var score: List<Score> = listOf()

):Parcelable