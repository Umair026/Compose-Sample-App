package com.umair.features.tweetsDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.umair.core.common.components.AppContent
import com.umair.core.common.components.CustomAppBarContent
import com.umair.features.tweets.TweetsUiState

@Composable
fun TweetDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: TweetDetailsViewModel = hiltViewModel(),
    onClick: () -> Unit
) {

    var tweetsState = viewModel.detail.collectAsStateWithLifecycle()
    TweetDetailContent(
        uiState = tweetsState.value,
        title = viewModel.getSelectedCategory() ?: "",
        navController = navController,
        onClick = onClick
    )
}

@Composable
fun TweetDetailContent(
    uiState: TweetsUiState,
    title: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){
    AppContent(
        showLoader = uiState.isLoading,
        toolBar = CustomAppBarContent(
            pageTitle = title,
            navigationIcon = Icons.Filled.KeyboardArrowLeft,
            backAction = {
                navController.popBackStack()
            },
        ),
        content = {
            LazyColumn(content = {
                items(uiState.tweet ?: emptyList()) {
                    TweetListItem(tweet = it.tweet, onClick = onClick)
                }
            })
        }
    )
}

@Composable
fun TweetListItem(modifier: Modifier = Modifier, tweet: String, onClick: () -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(1f)
            .padding(10.dp).
        clickable {
            onClick()
        },
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        content = {
            Text(
                text = tweet,
                modifier = modifier.padding(20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}