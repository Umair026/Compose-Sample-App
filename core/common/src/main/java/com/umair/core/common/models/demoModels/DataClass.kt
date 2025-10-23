package com.umair.core.common.models.demoModels


/*
* For Profile
* */
data class ProfileRequest(
    val userId: Int
)

data class ProfileResponse(
    val name: String,
    val email: String,
    val phone: String
)

/*
* For Login
* */
data class LoginRequest(
    val username: String,
    val password: String
)
data class LoginResponse(
    val token: String,
    val userId: Int,
    val message: String
)

/*
* For Transactions
* */
data class TransactionRequest(
    val userId: Int,
    val month: String
)
data class TransactionResponse(
    val totalAmount: Double,
    val transactionList: List<TransactionItem>
)
data class TransactionItem(
    val id: Int,
    val date: String,
    val amount: Double
)