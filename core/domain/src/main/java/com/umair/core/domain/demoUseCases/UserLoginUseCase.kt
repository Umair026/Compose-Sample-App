package com.umair.core.domain.demoUseCases

import com.umair.core.common.models.demoModels.LoginRequest
import com.umair.core.common.models.demoModels.LoginResponse
import com.umair.core.network.repos.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    operator fun invoke(username: String, password: String): Flow<LoginResponse> {
        return networkRepository.login(LoginRequest(username = username, password = password))
    }
}