package com.example.data.remote.models

import com.example.domain.models.ScheduleModel
import com.google.gson.annotations.SerializedName

data class ScheduleDto(
    @SerializedName("days")
    val days: List<String>? = null,
    @SerializedName("time")
    val time: String? = null,
)

fun ScheduleDto.toModel() = ScheduleModel(
    days = days.orEmpty(),
    time = time.orEmpty(),
)
