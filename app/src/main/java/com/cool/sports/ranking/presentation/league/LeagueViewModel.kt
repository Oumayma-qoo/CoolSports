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
import kotlinx.coroutines.Dispatchers
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

    fun init() {}


    private fun setLoading() {

        state.value = LeagueStateScreen.IsLoading(true)
    }

    fun getLeagueInfo(leagueId: Int, subLeagueId: String, groupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            setLoading()
            repository.getLeagueInfo(leagueId, subLeagueId, groupId)
                .zip(repository.getLeagueInfo2(leagueId, subLeagueId, groupId)) { state1, state2 ->
                    if (state2 is DataState.Success && state1 is DataState.Success) {
                        state1.value.leagueStanding2 = state2.value.leagueStanding
                    }
                    return@zip state1
                }.catch { e ->
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
                    when (it) {
                        is DataState.GenericError -> {
                            Log.d(TAG, " Called Generic error")
                            state.value = LeagueStateScreen.StatusFailed(it.error!!.message.toString())
                        }

                        is DataState.Success -> {
                            Log.d(TAG, "Enter SUCCESS")
                            val subLeagueId = it.value.leagueStanding2.firstOrNull()?.list?.firstOrNull()?.subId?: 0
                            if (it.value.leagueStanding2.isEmpty() ||
                                it.value.leagueStanding2.firstOrNull()?.list.isNullOrEmpty() ||
                                it.value.leagueStanding2.firstOrNull()?.list?.firstOrNull()?.subId == null
                            ) {
                                state.value = it.value.let { it1 ->
                                    LeagueStateScreen.Response(it1)
                                }
                            } else {
                                getLeagueInfo(leagueId,"$subLeagueId",groupId)
                            }
                        }
                    }

                }

        }
    }
}