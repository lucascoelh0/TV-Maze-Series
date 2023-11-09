package com.example.domain.usecases

import com.example.core.models.Resource
import com.example.domain.models.EpisodeModel
import com.example.domain.models.SearchShowModel
import com.example.domain.models.ShowModel
import com.example.domain.repositories.IShowsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ShowsUseCaseImplTest {

    @MockK
    private lateinit var repository: IShowsRepository
    private lateinit var useCase: IShowsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = ShowsUseCaseImpl(repository)
    }

    @Test
    fun `getShows returns data from showsRepository`() = runTest {
        val expectedData = flowOf(Resource.success(listOf(mockk<ShowModel>())))
        every { repository.getShows(1) } returns expectedData

        val result = useCase.getShows(1)

        assertEquals(expectedData, result)
    }

    @Test
    fun `searchShows returns data from showsRepository`() = runTest {
        val expectedData = flowOf(Resource.success(listOf(mockk<SearchShowModel>())))
        every { repository.searchShows("query") } returns expectedData

        val result = useCase.searchShows("query")

        assertEquals(expectedData, result)
    }

    @Test
    fun `getShowEpisodes returns data from showsRepository`() = runTest {
        val expectedData = flowOf(Resource.success(listOf(mockk<EpisodeModel>())))
        every { repository.getShowEpisodes(1) } returns expectedData

        val result = useCase.getShowEpisodes(1)

        assertEquals(expectedData, result)
    }
}
