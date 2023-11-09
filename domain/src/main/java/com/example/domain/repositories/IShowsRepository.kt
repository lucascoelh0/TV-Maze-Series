package com.example.domain.repositories

import com.example.core.models.Resource
import com.example.domain.models.EpisodeModel
import com.example.domain.models.SearchShowModel
import com.example.domain.models.ShowModel
import kotlinx.coroutines.flow.Flow

interface IShowsRepository {

    fun getShows(
        page: Int,
    ): Flow<Resource<List<ShowModel>>>

    fun searchShows(
        query: String,
    ): Flow<Resource<List<SearchShowModel>>>

    fun getShowEpisodes(
        showIds: Int,
    ): Flow<Resource<List<EpisodeModel>>>
}
