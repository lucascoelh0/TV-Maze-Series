package com.luminay.tvmazeseries.features.details.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.ext.formatSummary
import com.example.core.models.Resource
import com.example.core.models.Status
import com.example.domain.models.EpisodeModel
import com.example.domain.models.ScheduleModel
import com.example.domain.models.ShowModel
import com.luminay.tvmazeseries.R
import com.luminay.tvmazeseries.theme.Blue100
import com.luminay.tvmazeseries.theme.Blue80
import com.luminay.tvmazeseries.theme.TvMazeSeriesTheme
import com.luminay.tvmazeseries.ui.common.BottomSheet
import com.luminay.tvmazeseries.ui.common.ErrorMessage
import com.luminay.tvmazeseries.ui.common.GradientOverlay
import com.luminay.tvmazeseries.ui.common.LoadingIndicator
import com.luminay.tvmazeseries.ui.common.NoShowsFoundMessage
import com.luminay.tvmazeseries.utils.coilutils.debugPlaceholder
import com.luminay.tvmazeseries.utils.coilutils.setupBuilder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TvShowDetailsScreen(
    id: Int,
    navigator: DestinationsNavigator,
    showModel: ShowModel,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        TvShowContentDetails(
            showModel = showModel,
            onBack = { navigator.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TvShowContentDetails(
    showModel: ShowModel,
    onBack: () -> Unit,
    viewModel: TvShowDetailsViewModel = hiltViewModel(),
) {
    val episodes by viewModel.episodes.collectAsStateWithLifecycle(initialValue = null)
    val selectedSeason by viewModel.selectedSeason.collectAsStateWithLifecycle(initialValue = 0)
    val showBottomSheet by viewModel.showDetailsBottomSheet.collectAsStateWithLifecycle(initialValue = false)

    LaunchedEffect(Unit) {
        viewModel.fetchData(showModel.id)
    }

    if (showBottomSheet) {
        BottomSheet(
            onDismiss = { viewModel.onDismissRequest() },
            content = {
                EpisodeDetails(
                    episode = viewModel.clickedEpisode,
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        TvShowHeader(
            show = showModel,
            onBack = onBack,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Schedule(
            showModel.schedule,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    end = 16.dp,
                    start = 16.dp,
                )
        )

        RatingAndGenres(
            rating = showModel.rating.average,
            genres = showModel.genres,
        )

        Summary(
            summary = showModel.summary.formatSummary(),
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    end = 16.dp,
                    start = 16.dp,
                )
        )

        SeasonsStatus(
            episodes = episodes,
            selectedSeason = selectedSeason,
            onSeasonClick = { seasonNumber ->
                viewModel.setSelectedSeason(seasonNumber)
            },
            onEpisodeClick = { episode ->
                viewModel.clickedEpisode = episode
                viewModel.onShowDetailsRequested()
            },
            groupEpisodes = { groupedEpisodes ->
                viewModel.groupEpisodesBySeason(groupedEpisodes)
            },
            onRetry = {
                viewModel.fetchData(showModel.id)
            },
        )
    }
}

@Composable
fun TvShowHeader(
    show: ShowModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val headerHeight = 250.dp
    val gradientHeight = 100.dp

    Box(
        modifier = modifier
            .height(headerHeight)
            .fillMaxWidth()
    ) {
        ShowImageWithGradientOverlay(
            imageUrl = show.image.original,
            imageDescription = "${show.name} Poster",
            headerHeight = headerHeight,
            gradientHeight = gradientHeight,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
        BackButton(48.dp, onBack)
        ShowDetails(show, Modifier.align(Alignment.BottomStart))
    }
}

@Composable
fun ShowImageWithGradientOverlay(
    imageUrl: String,
    imageDescription: String,
    headerHeight: Dp,
    gradientHeight: Dp,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .setupBuilder(url = imageUrl, name = imageDescription),
        contentDescription = imageDescription,
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight),
        placeholder = debugPlaceholder(debugPreview = R.drawable.full_poster_placeholder),
        contentScale = ContentScale.Crop
    )
    GradientOverlay(
        modifier = modifier,
        height = gradientHeight,
    )
}

@Composable
fun BackButton(
    topPadding: Dp,
    onBack: () -> Unit
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = stringResource(id = R.string.back_description),
        modifier = Modifier
            .padding(top = topPadding, start = 16.dp)
            .clickable { onBack() }
            .size(32.dp)
            .clip(CircleShape)
            .background(Blue100),
        tint = Color.White
    )
}

@Composable
fun SeasonsStatus(
    episodes: Resource<List<EpisodeModel>>?,
    selectedSeason: Int,
    onSeasonClick: (Int) -> Unit,
    onEpisodeClick: (EpisodeModel) -> Unit,
    groupEpisodes: (List<EpisodeModel>) -> Map<Int, List<EpisodeModel>>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        when (episodes?.status) {
            Status.LOADING -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                contentAlignment = Alignment.Center,
            ) {
                LoadingIndicator()
            }

            Status.SUCCESS -> SeasonsSuccessContent(
                episodes,
                selectedSeason,
                onSeasonClick,
                onEpisodeClick,
                groupEpisodes,
            )

            else -> ErrorMessage(
                onRetry,
                stringResource(id = R.string.loading_error),
            )
        }
    }
}

@Composable
fun SeasonsSuccessContent(
    episodes: Resource<List<EpisodeModel>>?,
    selectedSeason: Int,
    onSeasonClick: (Int) -> Unit,
    onEpisodeClick: (EpisodeModel) -> Unit,
    groupEpisodes: (List<EpisodeModel>) -> Map<Int, List<EpisodeModel>>
) {
    episodes?.data?.let { episodesData ->
        if (episodesData.isNotEmpty()) {
            val seasonsWithEpisodes = remember { groupEpisodes(episodesData) }

            Seasons(
                seasonsWithEpisodes = seasonsWithEpisodes,
                onSeasonClick = onSeasonClick,
                onEpisodeClick = onEpisodeClick,
                selectedSeason = selectedSeason,
            )
        } else {
            NoShowsFoundMessage()
        }
    }
}

@Composable
fun ShowDetails(
    show: ShowModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = show.name,
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = stringResource(
                id = R.string.average_runtime,
                show.year,
                show.averageRuntime,
            ),
            style = TextStyle(
                color = Color.White,
                fontSize = 14.sp,
            )
        )
    }
}

@Composable
fun Schedule(
    schedule: ScheduleModel,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        items(schedule.days) {
            TextChip(
                text = "$it - ${schedule.time}",
            )
        }
    }
}

@Composable
fun TextChip(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(end = 8.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp,
            )
    ) {
        Text(
            text = text,
            color = Color.White,
        )
    }
}

@Composable
fun RatingAndGenres(
    rating: Double,
    genres: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(
                top = 8.dp,
                end = 8.dp,
                start = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (rating > -1.0) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(id = R.string.rating_icon),
                tint = Color.Yellow,
            )

            Text(
                text = String.format("%.1f", rating),
                color = Color.White,
                modifier = Modifier
                    .padding(
                        end = 12.dp,
                        start = 4.dp,
                    ),
            )
        }


        genres.forEach { genre ->
            TextChip(text = genre)
        }
    }
}

@Composable
fun Summary(
    summary: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val gradientHeight by remember {
        mutableStateOf(30.dp)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            Text(
                text = summary,
                maxLines = if (expanded) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                )
            )

            if (!expanded) {
                GradientOverlay(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    height = gradientHeight
                )
            }
        }

        Icon(
            painter = painterResource(
                id = if (expanded) {
                    R.drawable.ic_expand_less
                } else {
                    R.drawable.ic_expand_more
                },
            ),
            contentDescription = if (expanded) "Collapse" else "Expand",
            modifier = Modifier
                .size(24.dp)
                .animateContentSize(
                    animationSpec = tween(durationMillis = 300)
                ),
            tint = Color.White,
        )
    }
}

@Composable
fun Seasons(
    seasonsWithEpisodes: Map<Int, List<EpisodeModel>>,
    onSeasonClick: (Int) -> Unit,
    onEpisodeClick: (EpisodeModel) -> Unit,
    selectedSeason: Int,
) {
    val seasons = remember { seasonsWithEpisodes.keys.sorted() }
    val episodes = remember(selectedSeason) {
        seasonsWithEpisodes[selectedSeason] ?: listOf()
    }

    Column {
        Text(
            text = stringResource(id = R.string.seasons),
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    start = 16.dp,
                ),
            color = Color.White,
            fontSize = 18.sp,
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(seasons) { season ->
                SeasonItem(
                    season = season,
                    isSelected = season == selectedSeason,
                    onSeasonClick = onSeasonClick
                )
            }
        }

        LazyRow(
            modifier = Modifier.padding(8.dp)
        ) {
            items(episodes) { episode ->
                EpisodeItem(
                    episode = episode,
                    onEpisodeClick = onEpisodeClick
                )
            }
        }
    }
}

@Composable
fun SeasonItem(
    season: Int,
    isSelected: Boolean,
    onSeasonClick: (Int) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(CircleShape)
            .background(if (isSelected) Color.White else Blue80)
            .clickable { onSeasonClick(season) }
            .padding(12.dp)
            .wrapContentSize()
            .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
    ) {
        Text(
            text = season.toString(),
            color = if (isSelected) Blue100 else Color.White,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (isSelected) Blue100 else Color.White
            )
        )
    }
}

@Composable
fun EpisodeItem(
    episode: EpisodeModel,
    onEpisodeClick: (EpisodeModel) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(128.dp)
            .clickable { onEpisodeClick(episode) }
    ) {
        EpisodeImage(
            imageUrl = episode.image.original,
            episodeName = episode.name,
            height = 72.dp,
        )

        Text(
            text = "E${episode.number} - ${episode.name}",
            modifier = Modifier.padding(top = 4.dp),
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Composable
fun EpisodeImage(
    imageUrl: String,
    episodeName: String,
    height: Dp,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .setupBuilder(
                url = imageUrl,
                name = episodeName,
            ),
        contentDescription = stringResource(id = R.string.episode_image),
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(height),
        contentScale = ContentScale.Crop,
        placeholder = debugPlaceholder(debugPreview = R.drawable.poster_placeholder),
    )
}

@Preview
@Composable
private fun TvShowContentDetailsPreview() {
    TvShowContentDetails(
        showModel = ShowModel.MOCK,
        onBack = {},
    )
}

@Preview
@Composable
private fun SeasonsStatusPreview() {
    SeasonsStatus(
        episodes = Resource(
            status = Status.SUCCESS,
            data = listOf(EpisodeModel.MOCK),
            message = null,
            errorStatus = null,
        ),
        selectedSeason = 1,
        onSeasonClick = {},
        onEpisodeClick = {},
        groupEpisodes = { mapOf() },
        onRetry = {},
    )
}

@Preview
@Composable
private fun SeasonsSuccessContentPreview() {
    SeasonsSuccessContent(
        episodes = Resource(
            status = Status.SUCCESS,
            data = listOf(EpisodeModel.MOCK),
            message = null,
            errorStatus = null,
        ),
        selectedSeason = 1,
        onSeasonClick = {},
        onEpisodeClick = {},
        groupEpisodes = { mapOf() },
    )
}

@Preview
@Composable
fun SeasonItem() {
    SeasonItem(
        season = 1,
        isSelected = true,
        onSeasonClick = {},
    )
}

@Preview
@Composable
private fun TvShowHeaderPreview() {
    TvShowHeader(
        show = ShowModel.MOCK,
        onBack = {},
        modifier = Modifier
            .width(400.dp)
    )
}

@Preview
@Composable
private fun ShowImageWithGradientOverlayPreview() {
    ShowImageWithGradientOverlay(
        imageUrl = ShowModel.MOCK.image.original,
        imageDescription = ShowModel.MOCK.image.original,
        headerHeight = 200.dp,
        gradientHeight = 100.dp,
    )
}

@Preview
@Composable
private fun BackButtonPreview() {
    BackButton(
        topPadding = 16.dp,
        onBack = {},
    )
}

@Preview
@Composable
private fun ShowDetailsPreview() {
    ShowDetails(
        show = ShowModel.MOCK,
    )
}


@Preview
@Composable
private fun SchedulePreview() {
    Schedule(
        schedule = ScheduleModel.MOCK,
    )
}

@Preview
@Composable
private fun TextChipPreview() {
    TextChip(text = "Action")
}

@Preview
@Composable
private fun RatingAndGenresPreview() {
    RatingAndGenres(
        ShowModel.MOCK.rating.average,
        ShowModel.MOCK.genres,
    )
}

@Preview
@Composable
private fun SummaryPreview() {
    TvMazeSeriesTheme {
        Summary(
            summary = ShowModel.MOCK.summary,
        )
    }
}
