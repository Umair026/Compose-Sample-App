package com.umair.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Handle known HTTP errors
        when (response.code) {
            400 -> throw IOException("Bad Request (400)")
            401 -> throw IOException("Unauthorized (401)")
            403 -> throw IOException("Forbidden (403)")
            404 -> throw IOException("Not Found (404)")
            500 -> throw IOException("Internal Server Error (500)")
        }

        return response
    }
}