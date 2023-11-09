package com.example.data.remote.models

import com.example.domain.models.SeasonShowModel
import com.google.gson.annotations.SerializedName

data class EpisodeShowDto(
    @SerializedName("href")
    val href: String? = null,
)

fun EpisodeShowDto.toModel() = SeasonShowModel(
    href = href.orEmpty(),
)
