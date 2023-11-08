package com.example.data.remote.models

import com.example.domain.models.CountryModel
import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("timezone")
    val timezone: String? = null,
)

fun CountryDto.toModel() = CountryModel(
    code = code.orEmpty(),
    name = name.orEmpty(),
    timezone = timezone.orEmpty(),
)
