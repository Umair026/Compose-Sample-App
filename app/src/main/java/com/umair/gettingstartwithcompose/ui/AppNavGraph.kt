package com.umair.gettingstartwithcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.umair.feature.login.LoginView
import com.umair.feature.quotes.dataManager.DataManager
import com.umair.feature.quotes.screens.QuoteListScreen
import com.umair.features.tweets.TweetsCategoryScreen
import com.umair.features.tweetsDetails.TweetDetailsScreen
import com.umair.gettingstartwithcompose.ui.components.UIComponent
import com.umair.gettingstartwithcompose.ui.components.ChangeTheme

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "UIComponent") {
        composable(route = "UIComponent") {
            UIComponent(modifier = Modifier, navController)
        }
        composable(route = "LoginApp") {
            LoginView(LocalContext.current)
        }
        composable(route = "TweetsApp") {
            TweetsCategoryScreen(modifier = Modifier, navController = navController) {
                navController.navigate("TweetsDetail/$it")
            }
        }
        composable(
            route = "TweetsDetail/{category}",
            arguments = listOf(
                navArgument(name = "category") {
                    type = NavType.StringType
                }
            )
        ) {
            /*
            another way to pass argument

            val category = it.arguments?.getString("category")
            TweetDetailsScreen(modifier = Modifier, category)*/
            TweetDetailsScreen(modifier = Modifier, navController = navController)
        }
        composable(route = "ChangeTheme") {
            ChangeTheme(modifier = Modifier)
        }
        composable(route = "QuotesApp") {
            QuoteListScreen { DataManager.switchPages(it) }
        }
    }
}