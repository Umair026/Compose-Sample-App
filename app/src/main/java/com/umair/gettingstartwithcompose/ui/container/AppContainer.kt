package com.umair.gettingstartwithcompose.ui.container

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.umair.gettingstartwithcompose.ui.AppNavGraph

@Composable
fun AppContainer() {
    val navController = rememberNavController()
    AppNavGraph(modifier = Modifier, navController)
}