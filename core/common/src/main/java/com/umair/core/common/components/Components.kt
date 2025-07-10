package com.umair.core.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(
    toolBar: CustomAppBarContent,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit = {},
    showLoader: Boolean = false,
    loader: @Composable () -> Unit = { FullScreenLoader() }
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                content = toolBar,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = bottomBar,
        containerColor = Color.Transparent,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Box(modifier = Modifier.padding(it).fillMaxSize()) {
            content()
        }

        AnimatedVisibility(
            modifier = Modifier.zIndex(Int.MAX_VALUE.toFloat()),
            enter = fadeIn(),
            exit = fadeOut(),
            visible = showLoader,
            content = {
                loader()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    content: CustomAppBarContent,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            content.pageTitle?.let {
                Text(text = content.pageTitle)
            }
        },
        navigationIcon = {
            content.navigationIcon?.let {
                Box(
                    modifier = Modifier.padding(10.dp, 0.dp,0.dp,0.dp)
                ) {
                    ActionIcon(
                        onClick = content.backAction,
                        content = {
                            Icon(
                                imageVector = content.navigationIcon,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        },
        actions = {
            if (content.favouriteAction) {
                ActionIcon(
                    onClick = { },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                )
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            if (content.settingsAction) {
                ActionIcon(
                    onClick = { },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Localized description"
                        )
                    },
                )

            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = Color(0xFF4F4F4F),
            navigationIconContentColor = Color(0xFF4F4F4F),
        )
    )
}

@Composable
fun ActionIcon(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .clickable { onClick()  }
            .padding(2.dp) // Apply padding before clipping if you want padding inside the rounded shape
            .clip(RoundedCornerShape(5.dp)) // Apply the clip with rounded corners
            .background(Color(0xFFEEEEEE)) // Background of the clipped area
            .padding(3.dp),

        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

data class CustomAppBarContent(
    val pageTitle: String? = null,
    val navigationIcon: ImageVector? = null,
    val backAction: () -> Unit,
    val favouriteAction: Boolean = false,
    val settingsAction: Boolean = false
)