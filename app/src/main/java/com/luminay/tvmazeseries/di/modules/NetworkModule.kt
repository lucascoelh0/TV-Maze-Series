package com.luminay.tvmazeseries.di.modules

import android.content.Context
import com.example.data.remote.api.ShowsApi
import com.example.data.remote.interceptors.RequestInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.valentinilk.shimmer.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    @Singleton
    fun providesBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun providesConnectionTimeout() = NETWORK_TIMEOUT

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        connectionTimeout: Long,
        @ApplicationContext applicationContext: Context,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient
            .Builder()
            .readTimeout(connectionTimeout, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesNetworkResponseAdapterFactory(): NetworkResponseAdapterFactory = NetworkResponseAdapterFactory()

    @Singleton
    @Provides
    fun providesRetrofit(
        baseUrl: String,
        gson: Gson,
        client: OkHttpClient,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory,
    ): Retrofit =
        getRetrofit(
            baseUrl,
            gson,
            client,
            networkResponseAdapterFactory,
        )

    private fun getRetrofit(
        baseUrl: String,
        gson: Gson,
        client: OkHttpClient,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(networkResponseAdapterFactory)
        .build()

    @Singleton
    @Provides
    fun providesShowsApi(retrofit: Retrofit): ShowsApi = retrofit.create(ShowsApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.tvmaze.com/"
        private const val NETWORK_TIMEOUT = 60L
    }
}
