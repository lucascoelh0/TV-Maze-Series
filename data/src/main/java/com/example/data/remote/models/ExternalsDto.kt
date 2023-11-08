package com.example.data.remote.models

import com.example.domain.models.ExternalsModel
import com.google.gson.annotations.SerializedName

data class ExternalsDto(
    @SerializedName("imdb")
    val imdb: String? = null,
    @SerializedName("thetvdb")
    val theTvDb: Int? = null,
    @SerializedName("tvrage")
    val tvRage: Int? = null,
)

fun ExternalsDto.toModel() = ExternalsModel(
    imdb = imdb.orEmpty(),
    theTvDb = theTvDb ?: -1,
    tvRage = tvRage ?: -1,
)
