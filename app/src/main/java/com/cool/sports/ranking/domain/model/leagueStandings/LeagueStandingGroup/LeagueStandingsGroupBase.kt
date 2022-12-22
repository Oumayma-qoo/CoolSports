package com.cool.sports.ranking.domain.model.leagueStandings.LeagueStandingGroup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueStandingsGroupBase(
    @SerializedName("list")
    var list: List<GroupList> = listOf()
):Parcelable