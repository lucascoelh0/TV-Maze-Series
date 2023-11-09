package com.example.domain.models

import java.io.Serializable
import com.example.core.constants.EMPTY as EMPTY_STRING

data class ExternalsModel(
    val imdb: String,
    val theTvDb: Int,
    val tvRage: Int,
) : Serializable {
    companion object {
        val EMPTY = ExternalsModel(
            imdb = EMPTY_STRING,
            theTvDb = 0,
            tvRage = 0,
        )

        val MOCK = ExternalsModel(
            imdb = "8.1",
            theTvDb = 121361,
            tvRage = 24493,
        )
    }
}
