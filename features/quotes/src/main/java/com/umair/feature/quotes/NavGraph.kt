package com.umair.feature.quotes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.umair.core.common.extensions.safeNavigate
import com.umair.core.common.navigation.NavigationRoutes
import com.umair.feature.quotes.dataManager.DataManager
import com.umair.feature.quotes.screens.QuoteDetails
import com.umair.feature.quotes.screens.QuoteListScreen

fun NavGraphBuilder.quoteNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationRoutes.QUOTES_GRAPH,
        startDestination = NavigationRoutes.QUOTE_LIST
    ) {
        composable(NavigationRoutes.QUOTE_LIST) {
            QuoteListScreen(
                navController = navController,
                onclick = {
                    DataManager.currentQuote = it
                    navController.safeNavigate(
                        NavigationRoutes.TWEET_DETAILS,
                    )
                }
            )
        }
        composable(NavigationRoutes.TWEET_DETAILS) {
            QuoteDetails(DataManager.currentQuote, navController)
        }
    }
}