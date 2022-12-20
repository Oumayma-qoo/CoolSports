package com.example.coolsports.presentation.league

import android.util.Log
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coolsports.common.utils.DataState
import com.example.coolsports.data.retrofit.NoConnectionException
import com.example.coolsports.data.retrofit.NoInternetException
import com.example.coolsports.domain.model.league.BaseLeagueInfo2
import com.example.coolsports.domain.repository.Repository
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

}