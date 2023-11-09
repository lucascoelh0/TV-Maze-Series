package com.luminay.tvmazeseries.features.details.presentation.ui

import com.example.core.models.Resource
import com.example.domain.models.EpisodeModel
import com.example.domain.usecases.IShowsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TvShowDetailsViewModelTest {

    @MockK(relaxed = true)
    private lateinit var showsUseCase: IShowsUseCase

    private lateinit var viewModel: TvShowDetailsViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = TvShowDetailsViewModel(showsUseCase)
    }

    @Test
    fun `fetchData should emit success`() = runTest {
        val episodes = listOf(
            EpisodeModel.MOCK,
            EpisodeModel.MOCK,
        )
        coEvery { showsUseCase.getShowEpisodes(any()) } returns flowOf(Resource.success(episodes))

        viewModel.fetchData(1)

        val result = viewModel.episodes.take(2).toList()
        assertEquals(result, listOf(Resource.loading(null), Resource.success(episodes)))
    }

    @Test
    fun `groupEpisodesBySeason should group episodes correctly`() {
        val episodes = listOf(
            EpisodeModel.MOCK.copy(season = 2),
            EpisodeModel.MOCK,
            EpisodeModel.MOCK.copy(season = 2),
        )

        val grouped = viewModel.groupEpisodesBySeason(episodes)

        assertEquals(2, grouped.size)
        assertEquals(1, grouped[1]?.size)
        assertEquals(2, grouped[2]?.size)
    }

    @Test
    fun `setSelectedSeason should update selected season`() = runTest {
        viewModel.setSelectedSeason(2)
        val selectedSeason = viewModel.selectedSeason.first()
        assertEquals(2, selectedSeason)
    }
}
