package com.example.domain.models

data class LinksModel(
    val previousEpisode: PreviousEpisodeModel,
    val self: SelfModel,
) {
    companion object {
        val EMPTY = LinksModel(
            previousEpisode = PreviousEpisodeModel.EMPTY,
            self = SelfModel.EMPTY,
        )

        val MOCK = LinksModel(
            previousEpisode = PreviousEpisodeModel.MOCK,
            self = SelfModel.MOCK,
        )
    }
}
