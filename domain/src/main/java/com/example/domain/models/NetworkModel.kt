package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class NetworkModel(
    val country: CountryModel,
    val id: Int,
    val name: String,
    val officialSite: String,
) {
    companion object {
        val EMPTY = NetworkModel(
            country = CountryModel.EMPTY,
            id = -1,
            name = EMPTY_STRING,
            officialSite = EMPTY_STRING,
        )

        val MOCK = NetworkModel(
            country = CountryModel.MOCK,
            id = 1,
            name = "HBO",
            officialSite = "https://www.hbo.com/",
        )
    }
}
