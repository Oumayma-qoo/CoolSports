package com.cool.sports.ranking.domain.repository

import com.cool.sports.ranking.common.utils.DataState
import com.cool.sports.ranking.domain.model.league.BaseLeagueInfo
import com.cool.sports.ranking.domain.model.league.BaseLeagueInfo2
import com.cool.sports.ranking.domain.model.player.BasePlayerStanding
import com.cool.sports.ranking.domain.model.team.BaseTeam
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