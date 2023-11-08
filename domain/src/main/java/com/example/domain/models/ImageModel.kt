package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class ImageModel(
    val medium: String,
    val original: String,
) {
    companion object {
        val EMPTY = ImageModel(
            medium = EMPTY_STRING,
            original = EMPTY_STRING,
        )

        val MOCK = ImageModel(
            medium = "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
            original = "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg",
        )
    }
}
