package com.umair.core.network.repos

import com.umair.core.common.models.Tweet
import com.umair.core.common.models.TweetResponse
import com.umair.core.network.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface NetworkRepository {
    fun getCategories(): Flow<List<String>>
    fun getTweets(): Flow<TweetResponse>
    fun getTweets(category: String): Flow<ArrayList<Tweet>>
}

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiServices
) : NetworkRepository {
    override fun getCategories(): Flow<List<String>> {
        return flow {
            emit(apiService.getCategories())
        }
    }

    override fun getTweets(): Flow<TweetResponse> {
        return flow {
            emit(apiService.getTweets())
        }
    }

    override fun getTweets(category: String): Flow<ArrayList<Tweet>> {
        return flow {
            emit(apiService.getTweets("tweets[?(@.category==\"$category\")]"))
        }
    }
}