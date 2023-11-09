package com.example.data.repositories

import com.example.core.models.Status
import com.example.data.remote.NETWORK_ERROR
import com.example.data.remote.SERVER_ERROR
import com.example.data.remote.STATUS_ERROR
import com.example.data.remote.STATUS_OK
import com.example.data.remote.api.ShowsApi
import com.example.data.remote.models.EpisodeDto
import com.example.data.remote.models.GenericErrorResponse
import com.example.data.remote.models.SearchShowDto
import com.example.data.remote.models.ShowDto
import com.example.data.remote.models.toModel
import com.example.data.remote.repositories.ShowsRepositoryImpl
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import java.io.IOException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ShowsRepositoryImplTest {

    @MockK
    private lateinit var showsApi: ShowsApi
    private lateinit var showsRepository: ShowsRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        showsRepository = ShowsRepositoryImpl(showsApi)
    }

    @Test
    fun `getShows returns successful Resource`() = runTest {
        val expected = listOf(ShowDto())
        coEvery { showsApi.getShows(1) } returns NetworkResponse.Success(expected, code = STATUS_OK)

        val result = showsRepository.getShows(1).first()

        assertEquals(Status.SUCCESS, result.status)
        assertEquals(expected.toModel(), result.data)
    }

    @Test
    fun `getShows returns server error`() = runTest {
        val serverErrorResponse = GenericErrorResponse(SERVER_ERROR, STATUS_ERROR)
        coEvery {
            showsApi.getShows(1)
        } returns NetworkResponse.ServerError(serverErrorResponse, STATUS_ERROR)

        val result = showsRepository.getShows(1).first()

        assertEquals(Status.ERROR, result.status)
        assertEquals(STATUS_ERROR, result.errorStatus)
        assertEquals(SERVER_ERROR, result.message)
    }

    @Test
    fun `getShows returns network error`() = runTest {
        coEvery { showsApi.getShows(1) } returns NetworkResponse.NetworkError(IOException(NETWORK_ERROR))

        showsRepository.getShows(1).collect { result ->
            assertEquals(Status.ERROR, result.status)
        }
    }

    @Test
    fun `searchShows returns successful Resource`() = runTest {
        val expected = listOf(SearchShowDto())
        coEvery { showsApi.searchShows("1") } returns NetworkResponse.Success(expected, code = STATUS_OK)

        val result = showsRepository.searchShows("1").first()

        assertEquals(Status.SUCCESS, result.status)
        assertEquals(expected.toModel(), result.data)
    }

    @Test
    fun `searchShows returns server error`() = runTest {
        val serverErrorResponse = GenericErrorResponse(SERVER_ERROR, STATUS_ERROR)
        coEvery {
            showsApi.searchShows("1")
        } returns NetworkResponse.ServerError(serverErrorResponse, STATUS_ERROR)

        val result = showsRepository.searchShows("1").first()

        assertEquals(Status.ERROR, result.status)
        assertEquals(STATUS_ERROR, result.errorStatus)
        assertEquals(SERVER_ERROR, result.message)
    }

    @Test
    fun `searchShows returns network error`() = runTest {
        coEvery { showsApi.searchShows("1") } returns NetworkResponse.NetworkError(IOException(NETWORK_ERROR))

        showsRepository.searchShows("1").collect { result ->
            assertEquals(Status.ERROR, result.status)
        }
    }

    @Test
    fun `getShowEpisodes returns successful Resource`() = runTest {
        val expected = listOf(EpisodeDto())
        coEvery { showsApi.getShowEpisodes(1) } returns NetworkResponse.Success(expected, code = STATUS_OK)

        val result = showsRepository.getShowEpisodes(1).first()

        assertEquals(Status.SUCCESS, result.status)
        assertEquals(expected.toModel(), result.data)
    }

    @Test
    fun `getShowEpisodes returns server error`() = runTest {
        val serverErrorResponse = GenericErrorResponse(SERVER_ERROR, STATUS_ERROR)
        coEvery {
            showsApi.getShowEpisodes(1)
        } returns NetworkResponse.ServerError(serverErrorResponse, STATUS_ERROR)

        val result = showsRepository.getShowEpisodes(1).first()

        assertEquals(Status.ERROR, result.status)
        assertEquals(STATUS_ERROR, result.errorStatus)
        assertEquals(SERVER_ERROR, result.message)
    }

    @Test
    fun `getShowEpisodes returns network error`() = runTest {
        coEvery { showsApi.getShowEpisodes(1) } returns NetworkResponse.NetworkError(IOException(NETWORK_ERROR))

        showsRepository.getShowEpisodes(1).collect { result ->
            assertEquals(Status.ERROR, result.status)
        }
    }
}
