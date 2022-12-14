package com.example.coolsports.presentation.league
import com.example.coolsports.domain.model.league.BaseLeagueInfo

sealed class LeagueStateScreen {

    object Init : LeagueStateScreen()
    data class IsLoading(val isLoading: Boolean) : LeagueStateScreen()
    data class NoInternetException(val message:String): LeagueStateScreen()
    data class GeneralException(val message:String): LeagueStateScreen()
    data class StatusFailed(val message: String) : LeagueStateScreen()
    data class Response(val leagueInfo: BaseLeagueInfo) : LeagueStateScreen()
}