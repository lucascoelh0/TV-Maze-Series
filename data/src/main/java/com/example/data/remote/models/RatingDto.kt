package com.example.data.remote.models

import com.example.domain.models.RatingModel
import com.google.gson.annotations.SerializedName

data class RatingDto(
    @SerializedName("average")
    val average: Double? = null,
)

fun RatingDto.toModel() = RatingModel(
    average = average ?: -1.0,
)
