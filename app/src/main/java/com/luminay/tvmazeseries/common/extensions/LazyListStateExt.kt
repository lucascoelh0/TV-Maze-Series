package com.luminay.tvmazeseries.common.extensions

import androidx.compose.foundation.lazy.grid.LazyGridState

fun LazyGridState.isScrolledToEnd() = layoutInfo.visibleItemsInfo.any {
    it.index >= layoutInfo.totalItemsCount - 1
}
