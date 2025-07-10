package com.umair.core.domain.tweets

import com.umair.core.common.models.Tweet
import com.umair.core.network.repos.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TweetsDetailUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    operator fun invoke(category: String) : Flow<ArrayList<Tweet>> {
        return networkRepository.getTweets(category)
    }
}