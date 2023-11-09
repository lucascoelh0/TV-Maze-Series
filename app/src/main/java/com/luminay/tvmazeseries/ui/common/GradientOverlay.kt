package com.luminay.tvmazeseries.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.luminay.tvmazeseries.theme.Blue100

@Composable
fun GradientOverlay(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(Color.Transparent, Blue100),
    height: Dp
) {
    val gradientModifier = modifier
        .height(height)
        .fillMaxWidth()
        .background(
            brush = Brush.verticalGradient(
                colors = colors
            )
        )

    Box(modifier = gradientModifier)
}

