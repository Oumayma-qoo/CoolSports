package com.example.coolsports.data.retrofit

import android.content.Context
import com.example.coolsports.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NoConnectionException(private val context: Context): IOException() {
    override val message: String
        get() =context.getString(R.string.no_connection_exception)
}

class NoInternetException(private val context: Context) : IOException() {
    override val message: String
        get() = context.getString(R.string.no_internet_exception)
}

class RequestInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .build()
        return chain.proceed(newRequest)
    }
}
class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"

}