package com.umair.core.domain.demoUseCases

import com.umair.core.common.models.demoModels.TransactionRequest
import com.umair.core.common.models.demoModels.TransactionResponse
import com.umair.core.network.repos.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    operator fun invoke(userId: Int, month: String): Flow<TransactionResponse> =
        networkRepository.getTransactions(TransactionRequest(userId = userId, month = month))
}