package com.example.data.remote.api

import com.example.data.remote.models.GenericErrorResponse
import com.example.data.remote.models.ShowDto
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsApi {

    @GET("/shows")
    suspend fun getShows(
        @Query("page") page: Int = 0
    ): NetworkResponse<List<ShowDto>, GenericErrorResponse>
}
