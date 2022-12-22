package com.cool.sports.ranking.presentation.teamStandings

import com.cool.sports.ranking.domain.model.team.BaseTeam

sealed class TeamInfoScreenStanding {

    object Init : TeamInfoScreenStanding()
    data class IsLoading(val isLoading: Boolean) : TeamInfoScreenStanding()
    data class NoInternetException(val message:String): TeamInfoScreenStanding()
    data class GeneralException(val message:String): TeamInfoScreenStanding()
    data class StatusFailed(val message: String) : TeamInfoScreenStanding()
    data class Response(val teamInfo: BaseTeam) : TeamInfoScreenStanding()
}