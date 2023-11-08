package com.example.domain.usecases

import com.example.core.models.Resource
import com.example.domain.models.ShowModel
import com.example.domain.repositories.IShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowsUseCaseImpl @Inject constructor(
    private val showsRepository: IShowsRepository,
) : IShowsUseCase {

    override fun getShows(
        page: Int,
    ): Flow<Resource<List<ShowModel>>> = showsRepository.getShows(page)
}
