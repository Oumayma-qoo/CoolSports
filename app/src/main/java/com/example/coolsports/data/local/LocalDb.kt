package com.example.coolsports.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TeamInfoDto::class,TeamPlayerDto::class, PlayerDto::class], version = 1, exportSchema = false)

abstract class LocalDb : RoomDatabase() {
    abstract val daoTeamInfo: DaoTeamInfo
    abstract val daoPlayerInfo: DaoPlayerInfo
    abstract val daoPlayerStandings: DaoPlayerStandings


    companion object {
        const val DATABASE_NAME: String = "coolApp.db"

    }

}