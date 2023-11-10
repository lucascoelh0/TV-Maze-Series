package com.example.domain.usecases

import com.example.domain.models.FavoriteModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteUseCase {
    suspend fun insertFavorite(id: Int)
    suspend fun deleteFavorite(id: Int)
    fun getFavorites(): Flow<List<FavoriteModel>>
}
