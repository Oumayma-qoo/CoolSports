package com.cool.sports.ranking.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoDi {
    @Singleton
    @Provides
    fun getLocalDb(@ApplicationContext context: Context): LocalDb {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDb::class.java, LocalDb.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }


    @Singleton
    @Provides
    fun provideTeamInfoDao(localDb: LocalDb): DaoTeamInfo {
        return localDb.daoTeamInfo
    }


    @Singleton
    @Provides
    fun providePlayerInfoDao(localDb: LocalDb): DaoPlayerInfo {
        return localDb.daoPlayerInfo
    }

    @Singleton
    @Provides
    fun providePlayerStandingsDao(localDb: LocalDb): DaoPlayerStandings {
        return localDb.daoPlayerStandings
    }
}