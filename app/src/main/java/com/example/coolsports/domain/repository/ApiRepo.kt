package com.example.coolsports.domain.repository

import com.example.coolsports.common.utils.DataState
import com.example.coolsports.domain.model.match.BaseClassIndexNew
import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.player.BasePlayerStanding
import com.example.coolsports.domain.model.team.BaseTeam
import kotlinx.coroutines.flow.Flow

interface ApiRepo {

    suspend fun getHomeMatches(locale : String, pageNumber : String) : Flow<DataState<BaseClassIndexNew>>
    suspend fun getLeagueInfo(leagueId: Int, subLeagueId: String, groupId: Int) : Flow<DataState<BaseLeagueInfo>>
    suspend fun getPlayerStanding(leagueId: Int, season: String) : Flow<DataState<BasePlayerStanding>>
    suspend fun getTeamInfo(teamId: Int) : Flow<DataState<BaseTeam>>
}