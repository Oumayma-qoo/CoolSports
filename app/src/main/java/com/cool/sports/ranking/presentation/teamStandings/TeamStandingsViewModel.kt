package com.cool.sports.ranking.presentation.teamStandings

import android.util.Log
import androidx.lifecycle.*
import com.cool.sports.ranking.common.utils.DataState
import com.cool.sports.ranking.data.retrofit.NoConnectionException
import com.cool.sports.ranking.data.retrofit.NoInternetException
import com.cool.sports.ranking.domain.model.player.Player
import com.cool.sports.ranking.domain.model.team.TeamInfo
import com.cool.sports.ranking.domain.model.team.TeamPlayer
import com.cool.sports.ranking.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamStandingsViewModel @Inject constructor(private val repository: Repository) : ViewModel(),
    LifecycleObserver {

    val TAG: String = "TeamStandingsViewModel"

    private val state = MutableStateFlow<TeamInfoScreenStanding>(TeamInfoScreenStanding.Init)
    val mState: StateFlow<TeamInfoScreenStanding> get() = state

    private val teamLiveData = MutableLiveData<TeamInfo>()
    val _teamInfo: LiveData<TeamInfo> = teamLiveData




    private val MVPLiveData = MutableLiveData<TeamPlayer>()
    val _MVP: LiveData<TeamPlayer> = MVPLiveData


    private val playersListLiveData = MutableLiveData<List<Player>>()
    val _playersList: LiveData<List<Player>> = playersListLiveData


    private val playerPhotoLiveData = MutableLiveData<String>()
    val _playerPhoto: LiveData<String> = playerPhotoLiveData

    private val playerLiveData = MutableLiveData<TeamPlayer>()
    val _playerInfo: LiveData<TeamPlayer> = playerLiveData
    private fun setLoading() {

        state.value = TeamInfoScreenStanding.IsLoading(true)
    }

    private fun hideLoading() {

        state.value = TeamInfoScreenStanding.IsLoading(false)
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
                                state.value = TeamInfoScreenStanding.StatusFailed(it.error!!.message.toString())
                            }

                            is DataState.Success -> {
                                Log.d(TAG, "Enter SUCCESS")
                                state.value = it.value.let { it1 ->
                                    TeamInfoScreenStanding.Response(it1)
                                }
                            }
                        }
                    }
            }
            catch (e : Exception){
                when (e) {
                    is NoInternetException ->{
                        state.value = TeamInfoScreenStanding.NoInternetException(e.message)
                    }
                    is NoConnectionException -> {
                        state.value = TeamInfoScreenStanding.NoInternetException(e.message)
                    }
                    else -> {
                        state.value =
                            TeamInfoScreenStanding.GeneralException(e.message ?: "Exception Occurred")
                    }
                }
            }
        }
    }

     fun getTeamInfoFromLocalDB(teamId:Int){
        repository.getTeamInfoFromLocalDB(teamId).observeForever {
            teamLiveData.value= it

        }

    }


    fun getMVPFromLocalDB(teamId:Int){
        repository.getMVPFromLocalDB(teamId).observeForever {
            MVPLiveData.value= it

        }

    }
    fun getPlayerListOrderByGoals(teamId:Int){
        repository.getPlayerListOrderByGoals(teamId).observeForever {
            playersListLiveData.value= it

        }

    }

    fun getPlayerInfoFromLocalDB(playerId:Int, teamId:Int){
        repository.getPlayerInfoFromLocalBD(playerId, teamId).observeForever {
            playerLiveData.value= it

        }

    }
}