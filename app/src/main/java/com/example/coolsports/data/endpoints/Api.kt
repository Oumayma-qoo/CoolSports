package com.example.coolsports.data.endpoints

import com.example.coolsports.domain.model.BaseClassIndexNew
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/api/zqbf-list-page/{locale}/{page}")
    suspend fun getHomeMatchesData(@Path("locale") locale: String, @Path("page") pageNumber: String) : BaseClassIndexNew
}