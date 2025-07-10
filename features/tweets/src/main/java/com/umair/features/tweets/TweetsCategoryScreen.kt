package com.umair.features.tweets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.umair.core.common.components.AppContent
import com.umair.core.common.components.CustomAppBarContent

@Composable
fun TweetsCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onClick: (String) -> Unit
) {
    val viewModel: TweetsCategoryViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    TweetsCategoryContent(
        uiState = uiState.value,
        navController = navController,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun TweetsCategoryContent(
    uiState: TweetsUiState,
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    AppContent(
        showLoader = uiState.isLoading,
        toolBar = CustomAppBarContent(
            pageTitle = "Tweets Category",
            navigationIcon = Icons.Filled.KeyboardArrowLeft,
            backAction = {
                navController.popBackStack()
            },
            settingsAction = true,
        ),
        content = {
            Column(
                modifier = modifier.fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier.fillMaxSize(1f)
                        .padding(8.dp)
                ) {
                    items(uiState.categories ?: emptyList()) {
                        TweetCard(category = it, onClick)
                    }

                }
            }
        }
    )
}

@Composable
fun TweetCard(category: String, onClick: (String) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.padding(8.dp).size(160.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .size(160.dp)
                .clickable { onClick(category)  } // Apply the clip with rounded corners
                .paint(painter = painterResource(R.drawable.bg), contentScale = ContentScale.Crop)
                ,
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column {
                Text(
                    text = category,
                    fontSize = 18.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}
