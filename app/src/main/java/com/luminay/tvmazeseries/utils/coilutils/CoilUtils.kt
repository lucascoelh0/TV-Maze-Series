package com.luminay.tvmazeseries.utils.coilutils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.request.CachePolicy
import coil.request.ImageRequest.Builder

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }

fun Builder.setupBuilder(
    url: String,
    name: String,
) =
    crossfade(true)
        .data(url)
        .diskCacheKey("$name $url")
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
