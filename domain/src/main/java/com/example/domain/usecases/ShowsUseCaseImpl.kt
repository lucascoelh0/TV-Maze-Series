package com.example.domain.usecases

import com.example.core.models.Resource
import com.example.domain.models.EpisodeModel
import com.example.domain.models.ShowModel
import com.example.domain.repositories.IShowsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ShowsUseCaseImpl @Inject constructor(
    private val showsRepository: IShowsRepository,
) : IShowsUseCase {

    override fun getShows(
        page: Int,
    ): Flow<Resource<List<ShowModel>>> = showsRepository.getShows(page)

    override fun getShowEpisodes(
        showIds: Int,
    ): Flow<Resource<List<EpisodeModel>>> = showsRepository.getShowEpisodes(showIds)
}
