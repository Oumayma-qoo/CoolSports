package com.cool.sports.ranking.presentation.league

import android.util.Log
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cool.sports.ranking.common.utils.DataState
import com.cool.sports.ranking.data.retrofit.NoConnectionException
import com.cool.sports.ranking.data.retrofit.NoInternetException
import com.cool.sports.ranking.domain.model.league.BaseLeagueInfo2
import com.cool.sports.ranking.domain.repository.Repository
import com.cool.sports.ranking.presentation.playerStandings.PlayerStandingScreenState
import com.cool.sports.ranking.presentation.teamStandings.TeamInfoScreenStanding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LeagueViewModel @Inject constructor(private val repository: Repository) : ViewModel(),
    LifecycleObserver {

    val TAG: String = "LeagueViewModel"
    var queryLiveData = MutableLiveData<String>()

    private val state = MutableStateFlow<LeagueStateScreen>(LeagueStateScreen.Init)
    val mState: StateFlow<LeagueStateScreen> get() = state

    private val state1 = MutableStateFlow<TeamInfoScreenStanding>(TeamInfoScreenStanding.Init)
    val mState1: StateFlow<TeamInfoScreenStanding> get() = state1

    private val state2 = MutableStateFlow<PlayerStandingScreenState>(PlayerStandingScreenState.Init)
    val mState2: StateFlow<PlayerStandingScreenState> get() = state2


    fun init() {}


    private fun setLoading() {

        state.value = LeagueStateScreen.IsLoading(true)
    }

    private fun hideLoading() {

        state.value = LeagueStateScreen.IsLoading(false)
    }


    fun getLeagueInfo(leagueId: Int, subLeagueId: String, groupId: Int) {
        viewModelScope.launch {
            setLoading()
            repository.getLeagueInfo(leagueId, subLeagueId, groupId)
                .zip(repository.getLeagueInfo2(leagueId, subLeagueId, groupId)) { state1, state2 ->
                    if (state2 is DataState.Success && state1 is DataState.Success) {
                        hideLoading()
                        state1.value.leagueStanding2 = state2.value.leagueStanding
                    }
                    return@zip state1
                }.catch { e ->
                    delay(100)
                    when (e) {
                        is NoInternetException -> {
                            state.value = LeagueStateScreen.NoInternetException(e.message)
                        }
                        is NoConnectionException -> {
                            state.value = LeagueStateScreen.NoInternetException(e.message)
                        }
                        else -> {
                            state.value =
                                LeagueStateScreen.GeneralException(e.message ?: "Exception Occurred")
                        }
                    }
                }.collect {
                    Log.d(TAG, " Called collect")
                    delay(100)
                    when (it) {
                        is DataState.GenericError -> {
                            Log.d(TAG, " Called Generic error")
                            state.value = LeagueStateScreen.StatusFailed(it.error!!.message.toString())
                        }

                        is DataState.Success -> {
                            Log.d(TAG, "Enter SUCCESS")
                            state.value = it.value.let { it1 ->
                                LeagueStateScreen.Response(it1)
                            }
                        }
                    }
                }

        }
    }

    fun getTeamInfo(teamId: Int) {
        viewModelScope.launch {
            try {
                repository.getTeamInfo(teamId).onStart {
                    Log.d(TAG, " Called on start")
                    setLoading()

                }
                    .collect{
                        hideLoading()
                        Log.d(TAG, " Called collect")
                        when (it) {
                            is DataState.GenericError -> {
                                Log.d(TAG, " Called Generic error")
                                state1.value = TeamInfoScreenStanding.StatusFailed(it.error!!.message.toString())
                            }

                            is DataState.Success -> {
                                Log.d(TAG, "Enter SUCCESS")
                                state1.value = it.value.let { it1 ->
                                    TeamInfoScreenStanding.Response(it1)
                                }
                            }
                        }
                    }
            }
            catch (e : Exception){
                when (e) {
                    is NoInternetException ->{
                        state1.value = TeamInfoScreenStanding.NoInternetException(e.message)
                    }
                    is NoConnectionException -> {
                        state1.value = TeamInfoScreenStanding.NoInternetException(e.message)
                    }
                    else -> {
                        state1.value =
                            TeamInfoScreenStanding.GeneralException(e.message ?: "Exception Occurred")
                    }
                }
            }
        }
    }
    fun getPlayerStanding(leagueId: Int, season: String) {
        viewModelScope.launch {
            try {
                repository.getPlayerStanding(leagueId, season).onStart {
                    Log.d(TAG, " Called on start")
                    setLoading()

                }
                    .collect{
                        hideLoading()
                        Log.d(TAG, " Called collect")
                        when (it) {
                            is DataState.GenericError -> {
                                Log.d(TAG, " Called Generic error")
                                state2.value = PlayerStandingScreenState.StatusFailed(it.error!!.message.toString())
                            }

                            is DataState.Success -> {
                                Log.d(TAG, "Enter SUCCESS")
                                state2.value = it.value.let { it1 ->
                                    PlayerStandingScreenState.Response(it1)
                                }
                            }
                        }
                    }
            }
            catch (e : Exception){
                when (e) {
                    is NoInternetException ->{
                        state2.value = PlayerStandingScreenState.NoInternetException(e.message)
                    }
                    is NoConnectionException -> {
                        state2.value = PlayerStandingScreenState.NoInternetException(e.message)
                    }
                    else -> {
                        state2.value =
                            PlayerStandingScreenState.GeneralException(e.message ?: "Exception Occurred")
                    }
                }
            }
        }
    }



}