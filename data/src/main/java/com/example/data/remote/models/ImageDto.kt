package com.example.data.remote.models

import com.example.domain.models.ImageModel
import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("medium")
    val medium: String? = null,
    @SerializedName("original")
    val original: String? = null,
)

fun ImageDto.toModel() = ImageModel(
    medium = medium.orEmpty(),
    original = original.orEmpty(),
)
