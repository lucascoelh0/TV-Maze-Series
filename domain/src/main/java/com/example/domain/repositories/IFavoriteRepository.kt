package com.example.domain.repositories

import com.example.domain.models.FavoriteModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    suspend fun insertFavorite(id: Int)
    suspend fun deleteFavorite(id: Int)
    fun getFavorites(): Flow<List<FavoriteModel>>
}
