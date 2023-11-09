package com.example.domain.models

import com.example.core.constants.EMPTY as EMPTY_STRING

data class EpisodeModel(
    val links: SeasonLinksModel,
    val airDate: String,
    val airStamp: String,
    val airtime: String,
    val id: Int,
    val image: ImageModel,
    val name: String,
    val number: Int,
    val rating: RatingModel,
    val runtime: Int,
    val season: Int,
    val summary: String,
    val type: String,
    val url: String
) {
    companion object {
        val EMPTY = EpisodeModel(
            links = SeasonLinksModel.EMPTY,
            airDate = EMPTY_STRING,
            airStamp = EMPTY_STRING,
            airtime = EMPTY_STRING,
            id = 0,
            image = ImageModel.EMPTY,
            name = EMPTY_STRING,
            number = 0,
            rating = RatingModel.EMPTY,
            runtime = 0,
            season = 0,
            summary = EMPTY_STRING,
            type = EMPTY_STRING,
            url = EMPTY_STRING
        )

        val MOCK = EpisodeModel(
            links = SeasonLinksModel.MOCK,
            airDate = "2019-12-20",
            airStamp = "2019-12-20T00:00:00Z",
            airtime = "00:00",
            id = 123,
            image = ImageModel.MOCK,
            name = "Season 1",
            number = 1,
            rating = RatingModel.MOCK,
            runtime = 60,
            season = 1,
            summary = "Geralt of Rivia...",
            type = "Scripted",
            url = "https://api.tvmaze.com/seasons/1"
        )
    }
}
