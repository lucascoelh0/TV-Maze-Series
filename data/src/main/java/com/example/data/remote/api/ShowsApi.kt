package com.example.data.remote.api

import com.example.data.remote.models.EpisodeDto
import com.example.data.remote.models.GenericErrorResponse
import com.example.data.remote.models.SearchShowDto
import com.example.data.remote.models.ShowDto
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsApi {

    @GET("/shows")
    suspend fun getShows(
        @Query("page") page: Int = 0,
    ): NetworkResponse<List<ShowDto>, GenericErrorResponse>

    @GET("/search/shows")
    suspend fun searchShows(
        @Query("q") query: String,
    ): NetworkResponse<List<SearchShowDto>, GenericErrorResponse>

    @GET("/shows/{showId}/episodes")
    suspend fun getShowEpisodes(
        @Path("showId") id: Int,
    ): NetworkResponse<List<EpisodeDto>, GenericErrorResponse>
}
