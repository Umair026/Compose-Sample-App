package com.umair.core.network.interceptor

import com.umair.core.network.EncryptionUtils
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.nio.charset.Charset

class EncryptionInterceptor : Interceptor {
    private val isMock = true

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val proceedResponse: Response

        //throw IllegalArgumentException("Network Exception", null)
        // Encrypt request body
        if (request.body != null) {
            val buffer = Buffer()
            request.body?.writeTo(buffer)
            val charset = Charset.forName("UTF-8")
            val requestBodyString = buffer.readString(charset)
            val encryptedBody = EncryptionUtils.encrypt(requestBodyString)

            val newBody = encryptedBody
                .toRequestBody("application/json; charset=utf-8".toMediaType())

            request = request.newBuilder()
                .method(request.method, newBody)
                .build()
        }

        if(isMock) {
            val mockedEncryptedBody = when {
                request.url.encodedPath.endsWith("login") ->
                    "RoS1+oeFo2ITrFAkW8eRk9PXkEorcWKmqwxNpyMH1VXSfv2rWJMuJNHS8Q3CEyqgrNxjMPQ0lj6VN0IMHbFy+rZSXeyDAQGswCDLdpRh6ng="

                request.url.encodedPath.endsWith("profile") ->
                    "XbLU3UYaZ84LulOUGzBI7Kvq/XLt1eTF5eWPo34D/RSyDCFqzO7bJVWaY69WqWOgZEah+DGvJAQqfKiGVvkCKWjT1UjJvpXkwegNQQ+p92w="

                request.url.encodedPath.endsWith("transaction") ->
                    "8bfLCaHmyb7k44ofvm076JFbT0Rl2IWY0+GTR4KT9rU+hCNut006a+9bkTdo0N3TTVTDswN3a0kSKz/SqQ1vwp5K/NIglSa3osXbmJR5vB2vEHEBK133RguM6Up+qWgvgisHVl25DNkaNwuKb6wVgSGQKDI+54OBUoQNZzaaulMipK8APS13qhrfW7++Ni2k"

                else -> null
            }

            if (mockedEncryptedBody != null) {
                val mediaType = "application/json".toMediaType()
                val responseBody = mockedEncryptedBody.toResponseBody(mediaType)
                proceedResponse = Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(400)
                    .message("OK (mocked)")
                    //.code(500)
                    .message("Mock network exception")
                    .body(responseBody)
                    //.body("{}".toResponseBody("application/json".toMediaType()))
                    .build()
            } else {
                proceedResponse = chain.proceed(request)
            }
        } else {
            proceedResponse = chain.proceed(request)
        }

        if(proceedResponse.isSuccessful) {
            val responseBody = proceedResponse.body ?: return proceedResponse
            val contentType = responseBody.contentType()
            val bodyString = responseBody.string()

            val decryptedBodyString = try {
                EncryptionUtils.decrypt(bodyString)
            } catch (e: Exception) {
                bodyString // fallback if not encrypted
            }

            return proceedResponse.newBuilder()
                .body(decryptedBodyString.toResponseBody(contentType))
                .build()
        }

        return proceedResponse
    }
}


/*override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // Encrypt request body
        if (request.body != null) {
            val buffer = Buffer()
            request.body?.writeTo(buffer)
            val charset = Charset.forName("UTF-8")
            val requestBodyString = buffer.readString(charset)
            val encryptedBody = EncryptionUtils.encrypt(requestBodyString)

            val newBody = encryptedBody
                .toRequestBody("application/json; charset=utf-8".toMediaType())

            request = request.newBuilder()
                .method(request.method, newBody)
                .build()
        }

        // Proceed with encrypted request
        val response = chain.proceed(request)

        // Decrypt response body
        val responseBody = response.body ?: return response
        val contentType = responseBody.contentType()
        val bodyString = responseBody.string()

        val decryptedBodyString = try {
            EncryptionUtils.decrypt(bodyString)
        } catch (e: Exception) {
            bodyString // fallback if not encrypted
        }

        val newResponseBody = decryptedBodyString.toResponseBody(contentType)
        return response.newBuilder().body(newResponseBody).build()
    }*/