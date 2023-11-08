package com.example.domain.models

data class RatingModel(
    val average: Double,
) {
    companion object {
        val EMPTY = RatingModel(
            average = -1.0,
        )

        val MOCK = RatingModel(
            average = 8.0,
        )
    }
}
