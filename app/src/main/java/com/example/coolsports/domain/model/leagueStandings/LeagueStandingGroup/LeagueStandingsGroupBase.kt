package com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup

import com.google.gson.annotations.SerializedName

data class LeagueStandingsGroupBase(
    @SerializedName("list")
    var list: List<GroupList> = listOf()
)