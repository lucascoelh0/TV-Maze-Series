package com.luminay.tvmazeseries.features.details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ext.formatSummary
import com.example.domain.models.EpisodeModel
import com.luminay.tvmazeseries.R
import com.luminay.tvmazeseries.theme.Blue100

@Composable
fun EpisodeDetails(
    episode: EpisodeModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Blue100,
                shape = RoundedCornerShape(
                    topEnd = 16.dp,
                    topStart = 16.dp,
                ),
            ),
    ) {
        Column(
            modifier = modifier
                .padding(8.dp),
        ) {
            if (episode.image.original.isNotEmpty()) {
                EpisodeImage(
                    imageUrl = episode.image.original,
                    episodeName = episode.name,
                    height = 170.dp,
                )
            }

            Text(
                text = episode.name,
                modifier = Modifier
                    .padding(top = 16.dp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = stringResource(
                    id = R.string.episode_number_season,
                    episode.season,
                    episode.number,
                ),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                ),
            )

            if (episode.summary.isNotEmpty()) {
                Text(
                    text = episode.summary.formatSummary(),
                    modifier = Modifier.padding(top = 8.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                    ),
                )
            }
        }
    }
}
