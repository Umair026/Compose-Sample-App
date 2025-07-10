package com.umair.features.tweetsDetails

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.umair.features.tweets.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.umair.core.common.components.AppContent
import com.umair.core.common.components.CustomAppBarContent

@Composable
fun TweetDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val viewModel: TweetDetailsViewModel = hiltViewModel()

    val tweets = MutableStateFlow(listOf<String>())
    var tweetsState = tweets.collectAsState()
    viewModel.apply {
        CoroutineScope(Dispatchers.IO).launch {
            detail.collectLatest {
                when(it) {
                    is UIState.Error -> { }
                    UIState.Loading -> { }
                    is UIState.Success -> {
                        Log.d("UmairTweetsDetail", "${it.data}")
                        val list = it.data.filter { it.category == getSelectedCategory() }.map { it.tweet }
                        tweets.value = list
                    }
                }
            }
        }
    }

    AppContent(
        toolBar = CustomAppBarContent(
            pageTitle = viewModel.getSelectedCategory() ?: "",
            navigationIcon = Icons.Filled.KeyboardArrowLeft,
            backAction = {
                navController.popBackStack()
            },
        ),
        content = {
            LazyColumn(content = {
                items(tweetsState.value) {
                    TweetListItem(tweet = it)
                }
            })
        }
    )
}

@Composable
fun TweetListItem(modifier: Modifier = Modifier, tweet: String) {
    Card(
        modifier = modifier.fillMaxWidth(1f)
            .padding(10.dp),
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