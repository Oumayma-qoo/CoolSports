package com.example.coolsports.domain.repository

import android.util.Log
import com.example.coolsports.common.utils.DataState
import com.example.coolsports.data.endpoints.Api
import com.example.coolsports.domain.model.BaseClassIndexNew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api

) : ApiRepo {

    override suspend fun getHomeMatches(
        locale: String,
        pageNumber: String
    ): Flow<DataState<BaseClassIndexNew>> = flow {
        emit(DataState.Success(api.getHomeMatchesData(locale, pageNumber)))

    }



}
