package com.umair.core.network

import com.umair.core.common.models.demoModels.LoginRequest
import com.umair.core.common.models.demoModels.LoginResponse
import com.umair.core.common.models.demoModels.ProfileRequest
import com.umair.core.common.models.demoModels.ProfileResponse
import com.umair.core.common.models.demoModels.TransactionRequest
import com.umair.core.common.models.demoModels.TransactionResponse
import com.umair.core.common.models.Tweet
import com.umair.core.common.models.TweetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServices {

    @GET("/v3/b/686bc4d38960c979a5b89745?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String) : ArrayList<Tweet>

    @GET("/v3/b/686bc4d38960c979a5b89745?meta=false")
    suspend fun getTweets() : TweetResponse

    @GET("/v3/b/686bc4d38960c979a5b89745?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories() : List<String>



    /*
    * For Demo
    * */

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("profile")
    suspend fun getProfile(@Body request: ProfileRequest): ProfileResponse

    @POST("transaction")
    suspend fun getTransactions(@Body request: TransactionRequest): TransactionResponse

}