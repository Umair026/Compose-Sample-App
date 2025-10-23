package com.umair.core.network.repos

import com.umair.core.common.models.demoModels.LoginRequest
import com.umair.core.common.models.demoModels.LoginResponse
import com.umair.core.common.models.demoModels.ProfileRequest
import com.umair.core.common.models.demoModels.ProfileResponse
import com.umair.core.common.models.demoModels.TransactionRequest
import com.umair.core.common.models.demoModels.TransactionResponse
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

    fun login(request: LoginRequest): Flow<LoginResponse>
    fun getProfile(request: ProfileRequest): Flow<ProfileResponse>
    fun getTransactions(request: TransactionRequest): Flow<TransactionResponse>
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

    override fun login(request: LoginRequest): Flow<LoginResponse> {
        return flow {
            emit(apiService.login(request))
        }
    }
    override fun getProfile(request: ProfileRequest): Flow<ProfileResponse> {
        return flow {
            emit(apiService.getProfile(request))
        }

    }
    override fun getTransactions(request: TransactionRequest): Flow<TransactionResponse> {
        return flow {
            emit(apiService.getTransactions(request))
        }
    }
}