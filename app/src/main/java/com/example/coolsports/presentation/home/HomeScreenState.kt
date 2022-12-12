package com.example.coolsports.presentation.home

import com.example.coolsports.domain.model.BaseClassIndexNew

sealed class HomeScreenState {

    object Init : HomeScreenState()
    data class IsLoading(val isLoading: Boolean) : HomeScreenState()
    data class NoInternetException(val message:String): HomeScreenState()
    data class GeneralException(val message:String): HomeScreenState()
    data class StatusFailed(val message: String) : HomeScreenState()
    data class Response(val videos: BaseClassIndexNew) : HomeScreenState()
}