package com.umair.gettingstartwithcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.umair.core.common.components.AppContent
import com.umair.core.common.components.AppThemeButton
import com.umair.core.common.components.CustomAppBarContent
import com.umair.gettingstartwithcompose.ui.theme.GettingStartWithComposeTheme

@Composable
fun UIComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    AppContent(
        toolBar = CustomAppBarContent(
            pageTitle = "Sample app",
            backAction = { },
            favouriteAction = true,
            navigationIcon = Icons.Default.Home
        ),
        content = {
            Column {
                AppThemeButton(label = "LoginApp", onClick = {
                    navController.navigate("LoginApp")
                })
                AppThemeButton(label = "TweetsApp", onClick = {
                    navController.navigate("TweetsApp")
                })
                AppThemeButton(label = "ChangeTheme", onClick = {
                    navController.navigate("ChangeTheme")
                })
                AppThemeButton(label = "QuotesApp", onClick = {
                    navController.navigate("QuotesApp")
                })
            }

        }
    )
}

@Composable
fun ChangeTheme(modifier: Modifier = Modifier) {
    var theme = remember { mutableStateOf(false) }
    GettingStartWithComposeTheme(darkTheme = theme.value) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(1f)
                .background(MaterialTheme.colorScheme.background),
        ) {
            Text(
                text = "Hello World",
                color = MaterialTheme.colorScheme.primary

            )
            Button(
                onClick = {
                    theme.value = !theme.value
                }
            ) {
                Text(text = "change theme")
            }
        }
    }
}