package com.example.domain.models

data class SeasonLinksModel(
    val self: SelfModel,
    val show: SeasonShowModel,
) {
    companion object {
        val EMPTY = SeasonLinksModel(
            self = SelfModel.EMPTY,
            show = SeasonShowModel.EMPTY
        )

        val MOCK = SeasonLinksModel(
            self = SelfModel.MOCK,
            show = SeasonShowModel.MOCK
        )
    }
}
