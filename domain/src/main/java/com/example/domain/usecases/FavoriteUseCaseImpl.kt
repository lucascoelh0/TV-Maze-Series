package com.example.domain.usecases

import com.example.domain.models.FavoriteModel
import com.example.domain.repositories.IFavoriteRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: IFavoriteRepository,
) : IFavoriteUseCase {

    override suspend fun insertFavorite(id: Int) {
        favoriteRepository.insertFavorite(id)
    }

    override suspend fun deleteFavorite(id: Int) {
        favoriteRepository.deleteFavorite(id)
    }

    override fun getFavorites(): Flow<List<FavoriteModel>> {
        return favoriteRepository.getFavorites()
    }
}
