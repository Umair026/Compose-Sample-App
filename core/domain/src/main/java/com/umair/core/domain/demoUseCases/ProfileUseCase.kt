package com.umair.core.domain.demoUseCases

import com.umair.core.common.models.demoModels.ProfileRequest
import com.umair.core.common.models.demoModels.ProfileResponse
import com.umair.core.network.repos.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    operator fun invoke(userId: Int): Flow<ProfileResponse> =
        networkRepository.getProfile(ProfileRequest(userId))
}