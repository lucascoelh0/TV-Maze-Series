package com.example.data.utils

import com.example.core.models.Status
import com.example.data.remote.NETWORK_ERROR
import com.example.data.remote.SERVER_ERROR
import com.example.data.remote.STATUS_ERROR
import com.example.data.remote.STATUS_OK
import com.example.data.remote.SUCCESS_DATA
import com.example.data.remote.UNKNOWN_ERROR
import com.example.data.remote.models.GenericErrorResponse
import com.example.data.remote.utils.handleNetworkResponse
import com.haroldadmin.cnradapter.NetworkResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.IOException

class NetworkUtilsTest {

    private val mockTransform: (String) -> String = { it }

    @Test
    fun `handleNetworkResponse returns success resource on success response`() {
        val mockResponse: NetworkResponse<String, GenericErrorResponse> = NetworkResponse.Success(
            SUCCESS_DATA,
            code = STATUS_OK,
        )
        val result = handleNetworkResponse(mockResponse, mockTransform)
        assertEquals(Status.SUCCESS, result.status)
        assertEquals(SUCCESS_DATA, result.data)
        assertEquals(null, result.message)
        assertEquals(null, result.errorStatus)
    }

    @Test
    fun `handleNetworkResponse returns error resource on server error response`() {
        val errorResponse = GenericErrorResponse(message = SERVER_ERROR)
        val mockResponse: NetworkResponse<String, GenericErrorResponse> = NetworkResponse.ServerError(
            errorResponse,
            STATUS_ERROR,
        )
        val result = handleNetworkResponse(mockResponse, mockTransform)
        assertEquals(Status.ERROR, result.status)
        assertEquals(SERVER_ERROR, result.message)
        assertEquals(STATUS_ERROR, result.errorStatus)
        assertEquals(null, result.data)
    }

    @Test
    fun `handleNetworkResponse returns exception resource on network error response`() {
        val mockException = IOException(NETWORK_ERROR)
        val mockResponse: NetworkResponse<String, GenericErrorResponse> = NetworkResponse.NetworkError(mockException)
        val result = handleNetworkResponse(mockResponse, mockTransform)
        assertEquals(Status.ERROR, result.status)
        assertEquals(null, result.message)
        assertEquals(mockException, result.errorStatus)
        assertEquals(null, result.data)
    }

    @Test
    fun `handleNetworkResponse returns exception resource on unknown error response`() {
        val mockException = Exception(UNKNOWN_ERROR)
        val mockResponse: NetworkResponse<String, GenericErrorResponse> = NetworkResponse.UnknownError(mockException)
        val result = handleNetworkResponse(mockResponse, mockTransform)
        assertEquals(Status.ERROR, result.status)
        assertEquals(null, result.message)
        assertEquals(mockException, result.errorStatus)
        assertEquals(null, result.data)
    }
}
