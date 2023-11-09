package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class SeasonShowModel(
    val href: String
) {
    companion object {
        val EMPTY = SeasonShowModel(href = EMPTY_STRING)

        val MOCK = SeasonShowModel(href = "https://api.tvmaze.com/shows/250")
    }
}
