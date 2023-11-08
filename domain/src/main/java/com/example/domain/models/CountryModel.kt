package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class CountryModel(
    val code: String,
    val name: String,
    val timezone: String,
) {
    companion object {
        val EMPTY = CountryModel(
            code = EMPTY_STRING,
            name = EMPTY_STRING,
            timezone = EMPTY_STRING,
        )

        val MOCK = CountryModel(
            code = "US",
            name = "United States",
            timezone = "America/New_York",
        )
    }
}
