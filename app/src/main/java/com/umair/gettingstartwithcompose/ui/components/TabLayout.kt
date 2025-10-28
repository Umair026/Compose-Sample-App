package com.umair.gettingstartwithcompose.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabViewExample() {
    val tabs = listOf("Home", "Profile", "Settings")
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        // Animate scroll to selected tab
                        CoroutineScope(Dispatchers.Main).launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> HomeScreen()
                1 -> ProfileScreen()
                2 -> SettingsScreen()
            }
        }
    }
}


@Composable fun HomeScreen() = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Home") }
@Composable fun ProfileScreen() = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Profile") }
@Composable fun SettingsScreen() = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Settings") }