package com.example.data.remote.repositories

import com.example.core.models.Resource
import com.example.data.remote.api.ShowsApi
import com.example.data.remote.models.toModel
import com.example.data.remote.utils.handleNetworkResponse
import com.example.domain.models.ShowModel
import com.example.domain.repositories.IShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val showsApi: ShowsApi,
) : IShowsRepository {

    override fun getShows(
        page: Int,
    ): Flow<Resource<List<ShowModel>>> {
        return flow {
            val response = showsApi.getShows(
                page = page,
            )
            val resource = handleNetworkResponse(response) {
                it.toModel()
            }
            emit(resource)
        }
    }
}
