package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class SelfModel(
    val href: String,
) {
    companion object {
        val EMPTY = SelfModel(
            href = EMPTY_STRING,
        )
    }
}
