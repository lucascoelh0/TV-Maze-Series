package com.example.data.remote.models

import com.example.domain.models.PreviousEpisodeModel
import com.google.gson.annotations.SerializedName

data class PreviousEpisodeDto(
    @SerializedName("href")
    val href: String? = null,
)

fun PreviousEpisodeDto.toModel() = PreviousEpisodeModel(
    href = href.orEmpty(),
)
