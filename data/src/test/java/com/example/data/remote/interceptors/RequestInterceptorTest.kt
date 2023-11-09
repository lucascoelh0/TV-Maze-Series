package com.example.data.remote.interceptors

import com.example.data.BuildConfig
import com.example.data.remote.URL
import com.example.data.remote.interceptors.RequestInterceptor.Companion.X_RAPID_API_HOST
import com.example.data.remote.interceptors.RequestInterceptor.Companion.X_RAPID_API_KEY
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Test

class RequestInterceptorTest {

    @Test
    fun `RequestInterceptor modifies request with headers`() {
        val chain: Interceptor.Chain = mockk(relaxed = true)
        val request: Request = Request.Builder().url(URL).build()
        val response: Response = mockk(relaxed = true)

        every { chain.request() } returns request
        every { chain.proceed(any()) } returns response

        val requestInterceptor = RequestInterceptor()
        requestInterceptor.intercept(chain)

        verify {
            chain.proceed(
                withArg { modifiedRequest ->
                    TestCase.assertEquals(BuildConfig.API_KEY, modifiedRequest.header(X_RAPID_API_KEY))
                    TestCase.assertEquals(RequestInterceptor.API_HOST, modifiedRequest.header(X_RAPID_API_HOST))
                },
            )
        }
    }
}
