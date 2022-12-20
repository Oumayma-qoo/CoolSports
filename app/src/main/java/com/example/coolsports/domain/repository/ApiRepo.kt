package com.example.coolsports.domain.repository

import com.example.coolsports.common.utils.DataState
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.BaseLeagueInfo2
import com.example.coolsports.domain.model.player.BasePlayerStanding
import com.example.coolsports.domain.model.team.BaseTeam
import kotlinx.coroutines.flow.Flow

interface ApiRepo {

    suspend fun getLeagueInfo(
        leagueId: Int,
        subLeagueId: String,
        groupId: Int
    ): Flow<DataState<BaseLeagueInfo>>

    suspend fun getLeagueInfo2(
        leagueId: Int,
        subLeagueId: String,
        groupId: Int
    ): Flow<DataState<BaseLeagueInfo2>>

    suspend fun getPlayerStanding(
        leagueId: Int,
        season: String
    ): Flow<DataState<BasePlayerStanding>>

    suspend fun getTeamInfo(teamId: Int): Flow<DataState<BaseTeam>>
}