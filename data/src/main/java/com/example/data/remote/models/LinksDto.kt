package com.example.data.remote.models

import com.example.domain.models.LinksModel
import com.example.domain.models.PreviousEpisodeModel
import com.example.domain.models.SelfModel
import com.google.gson.annotations.SerializedName

data class LinksDto(
    @SerializedName("previousepisode")
    val previousEpisode: PreviousEpisodeDto? = null,
    @SerializedName("self")
    val self: SelfDto? = null,
)

fun LinksDto.toModel() = LinksModel(
    previousEpisode = previousEpisode?.toModel() ?: PreviousEpisodeModel.EMPTY,
    self = self?.toModel() ?: SelfModel.EMPTY,
)
