package com.luminay.tvmazeseries.features.home.presentation.ui

import com.example.core.models.Resource
import com.example.domain.models.SearchShowModel
import com.example.domain.models.ShowModel
import com.example.domain.usecases.IShowsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @MockK(relaxed = true)
    private lateinit var showsUseCase: IShowsUseCase

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(showsUseCase)
    }

    @Test
    fun `fetchData should emit success`() = runTest {
        val shows = listOf(
            ShowModel.MOCK,
            ShowModel.MOCK,
        )
        coEvery { showsUseCase.getShows(any()) } returns flowOf(Resource.success(shows))

        viewModel.fetchData()

        val successResult = viewModel.unifiedShows.first()
        assertEquals(Resource.success(shows), successResult)
    }

    @Test
    fun `fetchNextPage should emit loading then shows`() = runTest {
        val shows = listOf(
            ShowModel.MOCK,
            ShowModel.MOCK,
        )
        coEvery { showsUseCase.getShows(any()) } returns flowOf(Resource.success(shows))

        viewModel.fetchNextPage()

        val result = viewModel.paginationStatus.take(2).toList()
        assertEquals(result, listOf(Resource.loading(null), Resource.success(shows)))
    }

    @Test
    fun `searchShows should emit success with search results`() = runTest {
        val searchResultShows = listOf(
            SearchShowModel.MOCK,
        )
        val searchTerm = "search"
        coEvery { showsUseCase.searchShows(searchTerm) } returns flowOf(Resource.success(searchResultShows))

        viewModel.updateSearchQuery(searchTerm)
        viewModel.searchShows()

        val successResult = viewModel.unifiedShows.first()
        assertEquals(Resource.success(searchResultShows.map { it.show }), successResult)
    }
}
