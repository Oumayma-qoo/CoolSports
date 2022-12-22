package com.cool.sports.ranking.presentation.playerStandings

import com.cool.sports.ranking.domain.model.player.BasePlayerStanding

sealed class PlayerStandingScreenState {

    object Init : PlayerStandingScreenState()
    data class IsLoading(val isLoading: Boolean) : PlayerStandingScreenState()
    data class NoInternetException(val message:String): PlayerStandingScreenState()
    data class GeneralException(val message:String): PlayerStandingScreenState()
    data class StatusFailed(val message: String) : PlayerStandingScreenState()
    data class Response(val playerStanding: BasePlayerStanding) : PlayerStandingScreenState()
}