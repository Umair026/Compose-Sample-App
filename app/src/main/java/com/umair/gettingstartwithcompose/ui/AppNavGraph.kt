package com.umair.gettingstartwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.umair.core.common.navigation.NavigationRoutes
import com.umair.feature.login.LoginView
import com.umair.feature.quotes.quoteNavGraph
import com.umair.features.tweetNavGraph
import com.umair.gettingstartwithcompose.ui.components.ChangeTheme
import com.umair.gettingstartwithcompose.ui.components.UIComponent

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "ui-graph") {

        navigation(
            route = "ui-graph",
            startDestination = NavigationRoutes.UI_COMPONENTS
        ) {
            composable(route = NavigationRoutes.UI_COMPONENTS) {
                UIComponent(
                    modifier = Modifier,
                    navController = navController
                )
            }
            composable(route = NavigationRoutes.LOGIN) {
                LoginView(
                    context = LocalContext.current
                )
            }
            composable(route = "ChangeTheme") {
                ChangeTheme(modifier = Modifier)
            }
        }
        /*
        * Each feature module exposes its own navigation graph
        *
        * */
        tweetNavGraph(navController)
        quoteNavGraph(navController)
    }
}