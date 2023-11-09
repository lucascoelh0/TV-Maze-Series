package com.example.data.api

import com.example.data.remote.api.ShowsApi
import com.example.data.remote.models.EpisodeDto
import com.example.data.remote.models.SearchShowDto
import com.example.data.remote.models.ShowDto
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ShowsApiTest {

    @MockK
    private lateinit var mockApi: ShowsApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getShows invokes expected endpoint and returns response`() = runTest {
        val mockedResponse = NetworkResponse.Success(
            listOf(ShowDto()),
            code = STATUS_OK,
        )
        coEvery { mockApi.getShows(1) } returns mockedResponse

        val result = mockApi.getShows(1)

        assertEquals(mockedResponse, result)
        coVerify { mockApi.getShows(1) }
    }

    @Test
    fun `searchShows invokes expected endpoint and returns response`() = runTest {
        val mockedResponse = NetworkResponse.Success(
            listOf(SearchShowDto()),
            code = STATUS_OK,
        )
        coEvery { mockApi.searchShows("1") } returns mockedResponse

        val result = mockApi.searchShows("1")

        assertEquals(mockedResponse, result)
        coVerify { mockApi.searchShows("1") }
    }

    @Test
    fun `getShowEpisodes invokes expected endpoint and returns response`() = runTest {
        val mockedResponse = NetworkResponse.Success(
            listOf(EpisodeDto()),
            code = STATUS_OK,
        )
        coEvery { mockApi.getShowEpisodes(1) } returns mockedResponse

        val result = mockApi.getShowEpisodes(1)

        assertEquals(mockedResponse, result)
        coVerify { mockApi.getShowEpisodes(1) }
    }

    companion object {
        const val STATUS_OK = 200
    }
}
