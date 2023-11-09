package com.example.domain.models

data class SearchShowModel(
    val score: Double,
    val show: ShowModel,
) {
    companion object {
        val EMPTY = SearchShowModel(
            score = -1.0,
            show = ShowModel.EMPTY,
        )

        val MOCK = SearchShowModel(
            score = 8.1,
            show = ShowModel.MOCK,
        )
    }
}
