package com.example.coolsports.domain.model.league


data class LeagueEntity (
    var name: String = "",
    var nameShort: String = "",
    var leagueLogo: String = "",
    var type: String = "",
    var country: String = "",
    var currentSeason: String = "",
)