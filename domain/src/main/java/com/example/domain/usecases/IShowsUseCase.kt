package com.example.domain.usecases

import com.example.core.models.Resource
import com.example.domain.models.ShowModel
import kotlinx.coroutines.flow.Flow

interface IShowsUseCase {

    fun getShows(
        page: Int,
    ): Flow<Resource<List<ShowModel>>>
}