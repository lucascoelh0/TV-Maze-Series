package com.example.data.remote.models

import com.example.domain.models.SeasonLinksModel
import com.example.domain.models.SeasonShowModel
import com.example.domain.models.SelfModel
import com.google.gson.annotations.SerializedName

data class EpisodeLinksDto(
    @SerializedName("self")
    val self: SelfDto? = null,
    @SerializedName("show")
    val show: EpisodeShowDto? = null,
)

fun EpisodeLinksDto.toModel() = SeasonLinksModel(
    self = self?.toModel() ?: SelfModel.EMPTY,
    show = show?.toModel() ?: SeasonShowModel.EMPTY,
)

fun List<EpisodeLinksDto>.toModel() = map { it.toModel() }
