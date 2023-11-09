package com.example.data.remote.models

import com.example.domain.models.EpisodeModel
import com.example.domain.models.ImageModel
import com.example.domain.models.RatingModel
import com.example.domain.models.SeasonLinksModel
import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("_links")
    val links: EpisodeLinksDto? = null,
    @SerializedName("airdate")
    val airDate: String? = null,
    @SerializedName("airstamp")
    val airStamp: String? = null,
    @SerializedName("airtime")
    val airtime: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: ImageDto? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("rating")
    val rating: RatingDto? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("season")
    val season: Int? = null,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null,
)

fun EpisodeDto.toModel() = EpisodeModel(
    links = links?.toModel() ?: SeasonLinksModel.EMPTY,
    airDate = airDate.orEmpty(),
    airStamp = airStamp.orEmpty(),
    airtime = airtime.orEmpty(),
    id = id ?: -1,
    image = image?.toModel() ?: ImageModel.EMPTY,
    name = name.orEmpty(),
    number = number ?: -1,
    rating = rating?.toModel() ?: RatingModel.EMPTY,
    runtime = runtime ?: -1,
    season = season ?: -1,
    summary = summary.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty(),
)

fun List<EpisodeDto>.toModel() = map { it.toModel() }
