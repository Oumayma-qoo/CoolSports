package com.cool.sports.ranking.presentation.league
import com.cool.sports.ranking.domain.model.league.BaseLeagueInfo

sealed class LeagueStateScreen {

    object Init : LeagueStateScreen()
    data class IsLoading(val isLoading: Boolean) : LeagueStateScreen()
    data class NoInternetException(val message:String): LeagueStateScreen()
    data class GeneralException(val message:String): LeagueStateScreen()
    data class StatusFailed(val message: String) : LeagueStateScreen()
    data class Response(val leagueInfo: BaseLeagueInfo) : LeagueStateScreen()
}