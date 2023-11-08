package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class PreviousEpisodeModel(
    val href: String,
) {
    companion object {
        val EMPTY = PreviousEpisodeModel(
            href = EMPTY_STRING,
        )

        val MOCK = PreviousEpisodeModel(
            href = "https://api.tvmaze.com/episodes/1850540",
        )
    }
}
