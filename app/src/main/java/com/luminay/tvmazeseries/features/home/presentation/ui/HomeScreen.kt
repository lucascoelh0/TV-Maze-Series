package com.luminay.tvmazeseries.features.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.constants.EMPTY
import com.example.core.models.Resource
import com.example.core.models.Status
import com.example.domain.models.ShowModel
import com.luminay.tvmazeseries.R
import com.luminay.tvmazeseries.common.extensions.isScrolledToEnd
import com.luminay.tvmazeseries.destinations.TvShowDetailsScreenDestination
import com.luminay.tvmazeseries.theme.Blue80
import com.luminay.tvmazeseries.theme.Purple80
import com.luminay.tvmazeseries.ui.common.SearchBarWithBorder
import com.luminay.tvmazeseries.ui.common.TvShowItem
import com.luminay.tvmazeseries.ui.common.pullrefresh.PullRefreshIndicator
import com.luminay.tvmazeseries.ui.common.pullrefresh.pullRefresh
import com.luminay.tvmazeseries.ui.common.pullrefresh.rememberPullRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Destination
@Composable
fun HomeScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val showsResource by viewModel.unifiedShows.collectAsStateWithLifecycle(initialValue = Resource.loading(null))
    val searchTerm by viewModel.searchQuery.collectAsStateWithLifecycle(initialValue = EMPTY)

    Scaffold(
        topBar = {
            TopBar(
                searchTerm = searchTerm,
                onQueryChange = { value ->
                    viewModel.updateSearchQuery(value)
                    viewModel.searchShows()
                },
                onSearch = {
                    viewModel.searchShows()
                },
                isSearchBarEnabled = true,
            )
        }
    ) {
        TvShowStatus(
            paddingValues = it,
            showsResource = showsResource,
            searchQuery = searchTerm,
            onShowClick = { show ->
                navigator.navigate(
                    TvShowDetailsScreenDestination(
                        id = id,
                        showModel = show,
                    )
                )
            },
            onRetry = { viewModel.fetchData() },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun TvShowStatus(
    paddingValues: PaddingValues,
    showsResource: Resource<List<ShowModel>>?,
    searchQuery: String,
    onShowClick: (ShowModel) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (showsResource?.status) {
            Status.LOADING -> {
                CircularProgressIndicator(
                    color = Color.White,
                )
            }

            Status.SUCCESS -> {
                showsResource.data?.let { shows ->
                    if (shows.isNotEmpty()) {
                        TvShowsList(
                            shows = shows,
                            onShowClick = onShowClick,
                            searchQuery = searchQuery,
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.no_shows_found),
                            color = Color.White,
                        )
                    }
                } ?: run {
                    ErrorMessage(
                        onRetry = onRetry,
                        message = stringResource(id = R.string.loading_error),
                        modifier = modifier,
                    )
                }
            }

            else -> {
                ErrorMessage(
                    onRetry = onRetry,
                    message = stringResource(id = R.string.loading_error),
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    searchTerm: String,
    isSearchBarEnabled: Boolean,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = Blue80)
            .padding(
                top = 32.dp,
                end = 8.dp,
                bottom = 8.dp,
                start = 8.dp,
            )
            .fillMaxWidth(),
    ) {
        SearchBarWithBorder(
            searchTerm = searchTerm,
            onQueryChange = {
                onQueryChange(it)
            },
            onSearch = onSearch,
            isEnabled = isSearchBarEnabled,
        )
    }
}

@Composable
fun TvShowsList(
    shows: List<ShowModel>,
    searchQuery: String,
    onShowClick: (ShowModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val refreshScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val paginationStatus by viewModel.paginationStatus.collectAsStateWithLifecycle(initialValue = null)
    var isRefreshing by remember { mutableStateOf(false) }

    fun refresh() {
        if (searchQuery.isEmpty()) {
            refreshScope.launch {
                isRefreshing = true
                viewModel.fetchData()
            }
        }
    }

    val state = rememberPullRefreshState(isRefreshing, ::refresh)

    LaunchedEffect(Unit) {
        snapshotFlow {
            lazyGridState.isScrolledToEnd()
        }
            .distinctUntilChanged()
            .filter { it && !isRefreshing }
            .collect {
                viewModel.fetchNextPage()
            }
    }

    Box(modifier = modifier.pullRefresh(state)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = lazyGridState,
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items = shows) { show ->
                TvShowItem(
                    show = show,
                    modifier = Modifier.clickable {
                        onShowClick(show)
                    }
                )
            }

            item(
                span = {
                    GridItemSpan(3)
                }
            ) {
                when (paginationStatus?.status) {
                    Status.LOADING -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                color = Color.White,
                            )
                        }
                    }

                    Status.ERROR -> {
                        ErrorMessage(
                            onRetry = { viewModel.fetchNextPage() },
                            message = stringResource(id = R.string.loading_error),
                        )
                    }

                    else -> Unit
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = Color.White,
            contentColor = Purple80,
        )
    }
}

@Composable
fun ErrorMessage(
    onRetry: () -> Unit,
    message: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.White,
        )

        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple80,
            ),
        ) {
            Text(
                text = stringResource(id = R.string.try_again),
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(
        searchTerm = EMPTY,
        onQueryChange = {},
        onSearch = {},
        isSearchBarEnabled = true,
    )
}

@Preview
@Composable
fun TvShowsListPreview() {
    TvShowsList(
        shows = listOf(
            ShowModel.MOCK,
            ShowModel.MOCK,
        ),
        searchQuery = EMPTY,
        onShowClick = {},
    )
}

@Preview
@Composable
private fun ErrorMessagePreview() {
    ErrorMessage(
        onRetry = {},
        stringResource(id = R.string.loading_error),
    )
}
