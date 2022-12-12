package com.example.coolsports.data.retrofit

import com.example.coolsports.data.endpoints.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module(includes = [RetrofitDI::class])
@InstallIn(SingletonComponent :: class)
object ApiModule {

    @Provides
    fun providesApiClass(retrofit: Retrofit) : Api {
        return  retrofit.create(Api::class.java)
    }


}