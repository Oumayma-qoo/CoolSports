package com.cool.sports.ranking.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cool.sports.ranking.domain.model.player.Player
import com.cool.sports.ranking.domain.model.team.TeamInfo
import com.cool.sports.ranking.domain.model.team.TeamPlayer


@Dao
interface DaoTeamInfo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamInfo: TeamInfoDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(teams: List<TeamInfoDto>)

    @Query("SELECT * FROM teamInfo WHERE teamId = :teamId")
    suspend fun getTeamInfoById(teamId: Int): TeamInfo?

}

@Dao
interface DaoPlayerInfo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playerInfo: TeamPlayerDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(teams: List<TeamPlayerDto>)

    @Query("SELECT * FROM teamPlayerInfo WHERE playerId = :playerId AND teamID= :teamId")
    suspend fun getPlayerInfoById(playerId: Int, teamId: Int): TeamPlayer?


    @Query("SELECT * from teamPlayerInfo where value =(SELECT  MAX(value) from teamPlayerInfo) AND teamID= :teamId ")
    suspend fun getMVP(teamId: Int): TeamPlayer


    @Query("SELECT photo FROM teamPlayerInfo WHERE playerId = :playerId")
    suspend fun getPhotoByPlayerId(playerId: Int): String

}

@Dao
interface DaoPlayerStandings {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playerInfo: PlayerDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(players: List<PlayerDto>)


    @Query("SELECT * from players where  teamID= :teamId ORDER BY goals DESC")
    suspend fun getPlayerListOrderByGoals(teamId: Int): List<Player>


}
