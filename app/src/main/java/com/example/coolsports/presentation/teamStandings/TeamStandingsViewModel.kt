package com.example.coolsports.presentation.teamStandings

import androidx.lifecycle.*
import com.example.coolsports.common.utils.DataState
import com.example.coolsports.data.retrofit.NoConnectionException
import com.example.coolsports.data.retrofit.NoInternetException
import com.example.coolsports.domain.model.player.Player
import com.example.coolsports.domain.model.team.TeamInfo
import com.example.coolsports.domain.model.team.TeamPlayer
import com.example.coolsports.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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



    private val teamInfoLiveData = MutableLiveData< List<TeamInfo>>()
    val _teamInfoLiveData: LiveData< List<TeamInfo>> = teamInfoLiveData



    private val playerInfoLiveData = MutableLiveData< List<TeamPlayer>>()
    val _playerInfoLiveData: LiveData< List<TeamPlayer>> = playerInfoLiveData




    private val MVPLiveData = MutableLiveData<TeamPlayer>()
    val _MVP: LiveData<TeamPlayer> = MVPLiveData


    private val playersListLiveData = MutableLiveData<List<Player>>()
    val _playersList: LiveData<List<Player>> = playersListLiveData






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
                    setLoading()

                }
                    .onEach{
                        hideLoading()
                        when (it) {
                            is DataState.GenericError -> {
                                state.value = TeamInfoScreenStanding.StatusFailed(it.error!!.message.toString())
                            }
                            is DataState.Success -> {

                                teamInfoLiveData.value= it.value.teamInfoData
                                playerInfoLiveData.value= it.value.teamPlayerData

                            }
                        }
                    }.launchIn(viewModelScope)
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