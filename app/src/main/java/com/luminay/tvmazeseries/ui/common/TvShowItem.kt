package com.luminay.tvmazeseries.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.ShowModel
import com.luminay.tvmazeseries.theme.Blue100
import com.luminay.tvmazeseries.theme.Blue80

@Composable
fun TvShowItem(
    show: ShowModel,
    modifier: Modifier = Modifier,
    onFavoriteClick: (ShowModel) -> Unit = {},
) {
    Box(
        modifier = modifier
            .background(
                color = Blue80,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(2.dp)
            .width(120.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            TvShowImage(
                name = show.name,
                image = show.image.original,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(200.dp),
            )

            Text(
                text = show.name,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        end = 4.dp,
                        start = 4.dp,
                    ),
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Icon(
            imageVector = if (show.isFavorite) Icons.Default.Star else Icons.Outlined.Star,
            contentDescription = if (show.isFavorite) "Remove from favorites" else "Add to favorites",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(24.dp)
                .background(color = Blue100, shape = CircleShape)
                .padding(4.dp)
                .clickable { onFavoriteClick(show) },
            tint = if (show.isFavorite) Color.Yellow else Color.Gray,
        )
    }
}

@Preview
@Composable
private fun TvShowItemPreview() {
    TvShowItem(
        show = ShowModel.MOCK,
    )
}
