package com.example.domain.repositories

import com.example.core.models.Resource
import com.example.domain.models.ShowModel
import kotlinx.coroutines.flow.Flow

fun interface IShowsRepository {

    fun getShows(
        page: Int,
    ): Flow<Resource<List<ShowModel>>>
}
