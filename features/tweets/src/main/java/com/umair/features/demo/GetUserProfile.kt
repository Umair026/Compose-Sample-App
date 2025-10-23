package com.umair.features.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GetUserProfile(navController: NavHostController) {

    val viewModel: GetUserProfileViewModel = hiltViewModel()


    LaunchedEffect(key1 = Unit) {
        viewModel.viewModelScope.launch {
            viewModel.login(username = "umair.irshad", password = "abc12345678")

            delay(2000)
            viewModel.getProfile(userId = 123456789)

            delay(2000)
            viewModel.getTransactions(userId = 123456789, month = "January")
        }
    }


    viewModel.generateMockEncryptedResponses()

}