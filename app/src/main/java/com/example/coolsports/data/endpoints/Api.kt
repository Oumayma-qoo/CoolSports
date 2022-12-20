package com.example.coolsports.data.endpoints

import com.example.coolsports.domain.model.league.BaseLeagueInfo
import com.example.coolsports.domain.model.league.BaseLeagueInfo2
import com.example.coolsports.domain.model.player.BasePlayerStanding
import com.example.coolsports.domain.model.team.BaseTeam
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {


    @GET("/api/zqbf-list-league/{leagueId}/{subLeagueId}/{groupId}")
    suspend fun getLeagueInfoData(
        @Path("leagueId") leagueId: Int,
        @Path("subLeagueId") subLeagueId: String,
        @Path("groupId") groupId: Int
    ): BaseLeagueInfo

    @GET("/api/zqbf-list-league/{leagueId}/{subLeagueId}/{groupId}")
    suspend fun getLeagueInfoData2(
        @Path("leagueId") leagueId: Int,
        @Path("subLeagueId") subLeagueId: String,
        @Path("groupId") groupId: Int
    ): BaseLeagueInfo2

    @GET("/api/zqbf-list-jifen-top-scorer/{leagueId}/season/{season}")
    suspend fun getPlayerStandingData(
        @Path("leagueId") leagueID: Int,
        @Path("season") season: String
    ): BasePlayerStanding

    @GET("/api/zqbf-list-team/{teamId}")
    suspend fun getTeamInfoData(@Path("teamId") teamId: Int): BaseTeam
}