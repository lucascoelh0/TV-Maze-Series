package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.FavoriteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Entity(tableName = "Favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
)

fun FavoriteEntity.toModel() = FavoriteModel(
    id = id,
)

fun Flow<List<FavoriteEntity>>.toModel(): Flow<List<FavoriteModel>> =
    map { list ->
        list.map { favoriteEntity ->
            favoriteEntity.toModel()
        }
    }
