package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class ExternalsModel(
    val imdb: String,
    val theTvDb: Int,
    val tvRage: Int,
) {
    companion object {
        val EMPTY = ExternalsModel(
            imdb = EMPTY_STRING,
            theTvDb = 0,
            tvRage = 0,
        )
    }
}
