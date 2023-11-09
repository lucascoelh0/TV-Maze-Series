package com.example.core.models

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class ResourceTest {

    @Test
    fun `Resource success function creates a SUCCESS status resource`() {
        val resource = Resource.success(TEST_DATA)

        assertEquals(Status.SUCCESS, resource.status)
        assertEquals(TEST_DATA, resource.data)
        assertNull(resource.message)
        assertNull(resource.errorStatus)
    }

    @Test
    fun `Resource error function creates an ERROR status resource`() {
        val message = ERROR_MESSAGE
        val errorStatus = ERROR_STATUS
        val resource = Resource.error(message, TEST_DATA, errorStatus)

        assertEquals(Status.ERROR, resource.status)
        assertEquals(TEST_DATA, resource.data)
        assertEquals(message, resource.message)
        assertEquals(errorStatus, resource.errorStatus)
    }

    @Test
    fun `Resource exception function creates an ERROR status resource`() {
        val exception = Exception(TEST_EXCEPTION)
        val resource = Resource.exception(TEST_DATA, exception)
        assertEquals(Status.ERROR, resource.status)
        assertEquals(TEST_DATA, resource.data)
        assertNull(resource.message)
        assertEquals(exception, resource.errorStatus)
    }

    @Test
    fun `Resource loading function creates a LOADING status resource`() {
        val resource = Resource.loading<String>()
        assertEquals(Status.LOADING, resource.status)
        assertNull(resource.data)
        assertNull(resource.message)
        assertNull(resource.errorStatus)
    }

    companion object {
        private const val TEST_DATA = "TestData"
        private const val ERROR_MESSAGE = "ErrorMessage"
        private const val ERROR_STATUS = 404
        private const val TEST_EXCEPTION = "TestException"
    }
}
