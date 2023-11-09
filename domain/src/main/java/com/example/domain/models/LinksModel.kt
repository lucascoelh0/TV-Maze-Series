package com.example.domain.models

import java.io.Serializable

data class LinksModel(
    val previousEpisode: PreviousEpisodeModel,
    val self: SelfModel,
) : Serializable {
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
