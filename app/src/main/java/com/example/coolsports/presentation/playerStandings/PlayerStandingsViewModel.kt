package com.example.coolsports.presentation.playerStandings

import android.util.Log
import androidx.lifecycle.*
import com.example.coolsports.common.utils.DataState
import com.example.coolsports.data.retrofit.NoConnectionException
import com.example.coolsports.data.retrofit.NoInternetException
import com.example.coolsports.domain.model.team.TeamInfo
import com.example.coolsports.domain.model.team.TeamPlayer
import com.example.coolsports.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerStandingsViewModel @Inject constructor( val repository: Repository) : ViewModel(),
    LifecycleObserver {

    val TAG: String = "PlayerStandingsViewModel"

    private val state = MutableStateFlow<PlayerStandingScreenState>(PlayerStandingScreenState.Init)
    val mState: StateFlow<PlayerStandingScreenState> get() = state

    private val playerLiveData = MutableLiveData<TeamPlayer>()
    val _playerInfo: LiveData<TeamPlayer> = playerLiveData

    private fun setLoading() {

        state.value = PlayerStandingScreenState.IsLoading(true)
    }

    private fun hideLoading() {

        state.value = PlayerStandingScreenState.IsLoading(false)
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
                                state.value = PlayerStandingScreenState.StatusFailed(it.error!!.message.toString())
                            }

                            is DataState.Success -> {
                                Log.d(TAG, "Enter SUCCESS")
                                state.value = it.value.let { it1 ->
                                    PlayerStandingScreenState.Response(it1)
                                }
                            }
                        }
                    }
            }
            catch (e : Exception){
                when (e) {
                    is NoInternetException ->{
                        state.value = PlayerStandingScreenState.NoInternetException(e.message)
                    }
                    is NoConnectionException -> {
                        state.value = PlayerStandingScreenState.NoInternetException(e.message)
                    }
                    else -> {
                        state.value =
                            PlayerStandingScreenState.GeneralException(e.message ?: "Exception Occurred")
                    }
                }
            }
        }
    }


    fun getPlayerInfoFromLocalDB(playerId:Int){
        repository.getPlayerInfoFromLocalBD(playerId).observeForever {
            playerLiveData.value= it

        }

    }



}