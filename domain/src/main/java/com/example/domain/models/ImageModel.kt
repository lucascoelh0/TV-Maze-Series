package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class ImageModel(
    val medium: String? = null,
    val original: String? = null,
) {
    companion object {
        val EMPTY = ImageModel(
            medium = EMPTY_STRING,
            original = EMPTY_STRING,
        )
    }
}
