package com.luminay.tvmazeseries.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.luminay.tvmazeseries.R
import com.luminay.tvmazeseries.utils.coilutils.debugPlaceholder
import com.luminay.tvmazeseries.utils.coilutils.setupBuilder

@Composable
fun TvShowImage(
    name: String,
    image: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .setupBuilder(
                url = image,
                name = name,
            ),
        contentDescription = stringResource(id = R.string.tv_show_item_content_description),
        modifier = modifier,
        placeholder = debugPlaceholder(debugPreview = R.drawable.poster_placeholder),
        contentScale = ContentScale.Crop,
    )
}
