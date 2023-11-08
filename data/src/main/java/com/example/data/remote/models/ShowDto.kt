package com.example.data.remote.models

import com.example.domain.models.ExternalsModel
import com.example.domain.models.ImageModel
import com.example.domain.models.LinksModel
import com.example.domain.models.NetworkModel
import com.example.domain.models.RatingModel
import com.example.domain.models.ScheduleModel
import com.example.domain.models.ShowModel
import com.google.gson.annotations.SerializedName

data class ShowDto(
    @SerializedName("_links")
    val links: LinksDto? = null,
    @SerializedName("averageRuntime")
    val averageRuntime: Int? = null,
    @SerializedName("ended")
    val ended: String? = null,
    @SerializedName("externals")
    val externals: ExternalsDto? = null,
    @SerializedName("genres")
    val genres: List<String>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: ImageDto? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("network")
    val network: NetworkDto? = null,
    @SerializedName("officialSite")
    val officialSite: String? = null,
    @SerializedName("premiered")
    val premiered: String? = null,
    @SerializedName("rating")
    val rating: RatingDto? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("schedule")
    val schedule: ScheduleDto? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("updated")
    val updated: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("weight")
    val weight: Int? = null,
)

fun List<ShowDto>.toModel() = map { it.toModel() }

fun ShowDto.toModel() = ShowModel(
    links = links?.toModel() ?: LinksModel.EMPTY,
    averageRuntime = averageRuntime ?: -1,
    ended = ended.orEmpty(),
    externals = externals?.toModel() ?: ExternalsModel.EMPTY,
    genres = genres.orEmpty(),
    id = id ?: -1,
    image = image?.toModel() ?: ImageModel.EMPTY,
    language = language.orEmpty(),
    name = name.orEmpty(),
    network = network?.toModel() ?: NetworkModel.EMPTY,
    officialSite = officialSite.orEmpty(),
    premiered = premiered.orEmpty(),
    rating = rating?.toModel() ?: RatingModel.EMPTY,
    runtime = runtime ?: -1,
    schedule = schedule?.toModel() ?: ScheduleModel.EMPTY,
    status = status.orEmpty(),
    summary = summary.orEmpty(),
    type = type.orEmpty(),
    updated = updated ?: -1,
    url = url.orEmpty(),
    weight = weight ?: -1,
)
