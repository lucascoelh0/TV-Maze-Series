package com.example.domain.models

import java.io.Serializable
import com.example.core.constants.EMPTY as EMPTY_STRING

data class SelfModel(
    val href: String,
) : Serializable {
    companion object {
        val EMPTY = SelfModel(
            href = EMPTY_STRING,
        )

        val MOCK = SelfModel(
            href = "https://api.tvmaze.com/episodes/40005",
        )
    }
}
