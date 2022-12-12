package com.example.coolsports.domain.repository

import com.example.coolsports.common.utils.DataState
import com.example.coolsports.domain.model.BaseClassIndexNew
import kotlinx.coroutines.flow.Flow

interface ApiRepo {

    suspend fun getHomeMatches(locale : String, pageNumber : String) : Flow<DataState<BaseClassIndexNew>>

}