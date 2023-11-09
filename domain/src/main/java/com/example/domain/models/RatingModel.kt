package com.example.domain.models

import java.io.Serializable

data class RatingModel(
    val average: Double,
) : Serializable {
    companion object {
        val EMPTY = RatingModel(
            average = -1.0,
        )

        val MOCK = RatingModel(
            average = 8.0,
        )
    }
}
