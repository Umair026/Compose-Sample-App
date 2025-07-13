package com.umair.features

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.umair.core.common.extensions.safeNavigate
import com.umair.core.common.navigation.NavigationRoutes
import com.umair.features.tweets.TweetsCategoryScreen
import com.umair.features.tweetsDetails.TweetDetailsScreen

fun NavGraphBuilder.tweetNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationRoutes.TWEET_GRAPH,
        startDestination = NavigationRoutes.TWEET_CATEGORY
    ) {
        composable(NavigationRoutes.TWEET_CATEGORY) {
            TweetsCategoryScreen(
                modifier = Modifier,
                navController = navController
            ) {
                navController.safeNavigate("${NavigationRoutes.TWEET_DETAILS}/$it")
            }
        }
        composable(
            route = "${NavigationRoutes.TWEET_DETAILS}/{category}",
            arguments = listOf(
                navArgument(name = "category") {
                    type = NavType.StringType
                }
            )
        ) {
            TweetDetailsScreen(modifier = Modifier, navController = navController) {
                navController.safeNavigate(NavigationRoutes.QUOTES_GRAPH, builder = {
                    popUpTo(NavigationRoutes.TWEET_GRAPH){
                        inclusive = true
                    }
                })
            }
        }
    }
}