package com.umair.features.demo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.umair.core.domain.demoUseCases.ProfileUseCase
import com.umair.core.domain.demoUseCases.TransactionUseCase
import com.umair.core.domain.demoUseCases.UserLoginUseCase
import com.umair.core.network.EncryptionUtils
import com.umair.core.network.Resource
import com.umair.core.network.Resource.Loading.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetUserProfileViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val profileUseCase: ProfileUseCase,
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    fun login(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userLoginUseCase(username, password).asResult().collectLatest { response ->
                when (response) {
                    is Resource.Error -> {
                        Log.d("My-Okhttp Exception", "Error: ${response.errorDescription}")
                    }

                    Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d("My-Okhttp", "-->>> login "+response.toString())
                    }
                }
            }
        }
    }

    fun getProfile(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            profileUseCase(userId = userId).asResult().collectLatest { response ->
                when (response) {
                    is Resource.Error -> {
                        Log.d("My-Okhttp Exception", "Error: ${response.errorDescription}")
                    }

                    Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d("My-Okhttp", "-->>> getProfile "+response.toString())
                    }
                }
            }
        }
    }

    fun getTransactions(userId: Int, month: String) {
        CoroutineScope(Dispatchers.IO).launch {
            transactionUseCase(userId = userId, month = month).asResult()
                .collectLatest { response ->
                    when (response) {
                        is Resource.Error -> {
                            Log.d("My-Okhttp Exception", "Error: ${response.errorDescription}")
                        }

                        Resource.Loading -> {}
                        is Resource.Success -> {
                            Log.d("My-Okhttp", "-->>> getTransactions "+response.toString())
                        }
                    }
                }
        }
    }

    fun generateMockEncryptedResponses() {
        val loginResponse =
            """{"token":"abc123xyz", "userId": 123123, "message": "Login successful"}"""
        val profileResponse =
            """{"name":"Umair Irshad","email":"umair@example.com","phone": "+971502838980"}"""
        val transactionResponse =
            """{"totalAmount":2560.75,"transactionList":[{"id":1,"date":"2025-10-21","amount":1250.50},{"id":2,"date":"2025-10-22","amount":1310.25}]}"""

        println("Encrypted Login: ${EncryptionUtils.encrypt(loginResponse)}")
        println("Encrypted Profile: ${EncryptionUtils.encrypt(profileResponse)}")
        println("Encrypted Transaction: ${EncryptionUtils.encrypt(transactionResponse)}")
    }

}