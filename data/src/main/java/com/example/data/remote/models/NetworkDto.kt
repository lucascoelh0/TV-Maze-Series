package com.example.data.remote.models

import com.example.domain.models.CountryModel
import com.example.domain.models.NetworkModel
import com.google.gson.annotations.SerializedName

data class NetworkDto(
    @SerializedName("country")
    val country: CountryDto? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("officialSite")
    val officialSite: String? = null,
)

fun NetworkDto.toModel() = NetworkModel(
    country = country?.toModel() ?: CountryModel.EMPTY,
    id = id ?: -1,
    name = name.orEmpty(),
    officialSite = officialSite.orEmpty(),
)
