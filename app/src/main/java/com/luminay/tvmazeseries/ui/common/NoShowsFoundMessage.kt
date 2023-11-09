package com.luminay.tvmazeseries.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.luminay.tvmazeseries.R

@Composable
fun NoShowsFoundMessage() {
    Text(
        text = stringResource(id = R.string.no_shows_found),
        color = Color.White,
    )
}
