package com.example.coolsports.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coolsports.domain.model.player.Player
import com.example.coolsports.domain.model.team.TeamInfo
import com.example.coolsports.domain.model.team.TeamPlayer


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

    @Query("SELECT * FROM teamPlayerInfo WHERE playerId = :playerId")
    suspend fun getPlayerInfoById(playerId: Int): TeamPlayer?


    @Query("SELECT * from teamPlayerInfo where value =(SELECT  MAX(value) from teamPlayerInfo) ")
    suspend fun getMVP(): TeamPlayer

}
@Dao
interface DaoPlayerStandings {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playerInfo: PlayerDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(players: List<PlayerDto>)


    @Query("SELECT * from players where  teamID= :teamId ORDER BY goals DESC")
    suspend fun getPlayerListOrderByGoals(teamId:Int): List<Player>

}
