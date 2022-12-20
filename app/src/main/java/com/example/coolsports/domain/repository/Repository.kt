package com.example.coolsports.domain.repository

import androidx.lifecycle.liveData
import com.example.coolsports.common.utils.DataState
import com.example.coolsports.common.utils.DataState.Success
import com.example.coolsports.data.endpoints.Api
import com.example.coolsports.data.local.DaoPlayerInfo
import com.example.coolsports.data.local.DaoPlayerStandings
import com.example.coolsports.data.local.DaoTeamInfo
import com.example.coolsports.domain.mapper.NetworkMapperPlayer
import com.example.coolsports.domain.mapper.NetworkMapperPlayerStandings
import com.example.coolsports.domain.mapper.NetworkMapperTeam
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.BaseLeagueInfo2
import com.example.coolsports.domain.model.player.BasePlayerStanding
import com.example.coolsports.domain.model.team.BaseTeam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: Api,
    private val localNetworkMapperTeam: NetworkMapperTeam,
    private var daoTeamInfo: DaoTeamInfo,
    private val localNetworkMapperPlayer: NetworkMapperPlayer,
    private var daoPlayerInfo: DaoPlayerInfo,
    private val localNetworkMapperPlayerStandings: NetworkMapperPlayerStandings,
    private var daoPlayer: DaoPlayerStandings

) : ApiRepo {


    override suspend fun getLeagueInfo(
        leagueId: Int,
        subLeagueId: String,
        groupId: Int
    ): Flow<DataState<BaseLeagueInfo>> = flow {
        emit(Success(api.getLeagueInfoData(leagueId, subLeagueId, groupId)))
    }

    override suspend fun getLeagueInfo2(
        leagueId: Int,
        subLeagueId: String,
        groupId: Int
    ): Flow<DataState<BaseLeagueInfo2>> = flow {
        emit(Success(api.getLeagueInfoData2(leagueId, subLeagueId, groupId)))

    }

    override suspend fun getPlayerStanding(
        leagueId: Int,
        season: String
    ): Flow<DataState<BasePlayerStanding>> = flow {
        emit(Success(api.getPlayerStandingData(leagueId, season)))
        val result = api.getPlayerStandingData(leagueId, season)
        daoPlayer.insertMultiple(result.list.map { localNetworkMapperPlayerStandings.mapToDomain(it) })

    }

    override suspend fun getTeamInfo(teamId: Int): Flow<DataState<BaseTeam>> = flow {
        emit(Success(api.getTeamInfoData(teamId)))
        val result = api.getTeamInfoData(teamId)

        daoTeamInfo.insertMultiple(result.teamInfoData.map { localNetworkMapperTeam.mapToDomain(it) })
        daoPlayerInfo.insertMultiple(result.teamPlayerData.map {
            localNetworkMapperPlayer.mapToDomain(
                it
            )
        })

    }

    fun getTeamInfoFromLocalDB(teamId: Int) = liveData {
        emit(daoTeamInfo.getTeamInfoById(teamId))

    }

    fun getPlayerInfoFromLocalBD(playerId: Int) = liveData {
        emit(daoPlayerInfo.getPlayerInfoById(playerId))
    }

    fun getMVPFromLocalDB() = liveData {
        emit(daoPlayerInfo.getMVP())
    }


    fun getPlayerListOrderByGoals(teamId: Int) = liveData {
        emit(daoPlayer.getPlayerListOrderByGoals(teamId))
    }

}
