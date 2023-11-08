package com.example.data.remote.models

import com.example.domain.models.SelfModel
import com.google.gson.annotations.SerializedName

data class SelfDto(
    @SerializedName("href")
    val href: String? = null,
)

fun SelfDto.toModel() = SelfModel(
    href = href.orEmpty(),
)
