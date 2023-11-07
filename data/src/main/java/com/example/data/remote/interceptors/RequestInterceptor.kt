package com.example.data.remote.interceptors

import com.example.data.BuildConfig
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .addHeader(X_RAPID_API_KEY, BuildConfig.API_KEY)
            .addHeader(X_RAPID_API_HOST, API_HOST)
            .url(url)
            .build()

        return chain.proceed(request)
    }

    companion object {
        const val X_RAPID_API_KEY = "X-RapidAPI-Key"
        const val X_RAPID_API_HOST = "X-RapidAPI-Host"
        const val API_HOST = "tvjan-tvmaze-v1.p.rapidapi.com"
    }
}
