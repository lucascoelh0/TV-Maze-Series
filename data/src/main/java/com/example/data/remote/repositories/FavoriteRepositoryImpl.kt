package com.example.data.remote.repositories

import com.example.domain.dao.FavoriteDao
import com.example.domain.entities.FavoriteEntity
import com.example.domain.entities.toModel
import com.example.domain.repositories.IFavoriteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteShowDao: FavoriteDao
) : IFavoriteRepository {

    override suspend fun insertFavorite(id: Int) {
        favoriteShowDao.insertFavorite(FavoriteEntity(id))
    }

    override suspend fun deleteFavorite(id: Int) {
        favoriteShowDao.deleteFavorite(FavoriteEntity(id))
    }

    override fun getFavorites() = favoriteShowDao.getAllFavorites().toModel()
}
