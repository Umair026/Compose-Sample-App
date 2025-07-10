package com.umair.core.domain.tweets

import com.umair.core.network.repos.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TweetsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    operator fun invoke() : Flow<List<String>> {
        return networkRepository.getCategories()
    }
}