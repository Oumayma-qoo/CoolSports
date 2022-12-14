package com.example.coolsports.presentation.teamStandings

import com.example.coolsports.domain.model.team.BaseTeam

sealed class TeamInfoScreenStanding {

    object Init : TeamInfoScreenStanding()
    data class IsLoading(val isLoading: Boolean) : TeamInfoScreenStanding()
    data class NoInternetException(val message:String): TeamInfoScreenStanding()
    data class GeneralException(val message:String): TeamInfoScreenStanding()
    data class StatusFailed(val message: String) : TeamInfoScreenStanding()
    data class Response(val teamInfo: BaseTeam) : TeamInfoScreenStanding()
}