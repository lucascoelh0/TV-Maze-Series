package com.example.domain.models

import java.io.Serializable
import com.example.core.constants.EMPTY as EMPTY_STRING

data class ScheduleModel(
    val days: List<String>,
    val time: String,
) : Serializable {
    companion object {
        val EMPTY = ScheduleModel(
            days = emptyList(),
            time = EMPTY_STRING,
        )

        val MOCK = ScheduleModel(
            days = listOf("Monday"),
            time = "22:00",
        )
    }
}
