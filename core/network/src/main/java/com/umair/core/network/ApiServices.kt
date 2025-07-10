package com.umair.core.network

import com.umair.core.common.models.Tweet
import com.umair.core.common.models.TweetResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiServices {

    @GET("/v3/b/686bc4d38960c979a5b89745?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String) : ArrayList<Tweet>

    @GET("/v3/b/686bc4d38960c979a5b89745?meta=false")
    suspend fun getTweets() : TweetResponse

    @GET("/v3/b/686bc4d38960c979a5b89745?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories() : List<String>

}