package com.example.coolsports.presentation.league

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coolsports.common.utils.DataState
import com.example.coolsports.data.retrofit.NoConnectionException
import com.example.coolsports.data.retrofit.NoInternetException
import com.example.coolsports.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LeagueViewModel @Inject constructor(private val repository: Repository) : ViewModel(),
    LifecycleObserver {

    val TAG: String = "HomeViewModel"

    private val state = MutableStateFlow<LeagueStateScreen>(LeagueStateScreen.Init)
    val mState: StateFlow<LeagueStateScreen> get() = state


    private fun setLoading() {

        state.value = LeagueStateScreen.IsLoading(true)
    }

    private fun hideLoading() {

        state.value = LeagueStateScreen.IsLoading(false)
    }



    fun getLeagueInfo(leagueId: Int, subLeagueId: String, groupId: Int) {
        viewModelScope.launch {
            try {
                repository.getLeagueInfo(leagueId, subLeagueId,groupId).onStart {
                    Log.d(TAG, " Called on start")
                    setLoading()

                }
                    .collect{
                        hideLoading()
                        Log.d(TAG, " Called collect")
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
            catch (e : Exception){
                when (e) {
                    is NoInternetException ->{
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
            }
        }
    }

}