package com.example.data.remote.models

import com.example.domain.models.SearchShowModel
import com.example.domain.models.ShowModel
import com.google.gson.annotations.SerializedName

data class SearchShowDto(
    @SerializedName("score")
    val score: Double? = null,
    @SerializedName("show")
    val show: ShowDto? = null,
)

fun List<SearchShowDto>.toModel() = map { it.toModel() }

fun SearchShowDto.toModel() = SearchShowModel(
    score = score ?: -1.0,
    show = show?.toModel() ?: ShowModel.EMPTY,
)
