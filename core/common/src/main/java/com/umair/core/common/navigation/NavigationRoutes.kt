package com.umair.core.common.navigation

/*
* Define route constants (shared)
*
* */
object NavigationRoutes {
    const val UI_COMPONENTS = "ui-components"
    const val AUTH_GRAPH = "auth-graph"
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val TWEET_GRAPH = "tweet-app-graph"
    const val TWEET_CATEGORY = "tweet-category"
    const val TWEET_DETAILS = "tweet-details"
    const val CHANGE_THEME = "change-theme"
    const val QUOTES_GRAPH = "quote-app-graph"
    const val QUOTE_LIST = "quote-list"
    const val QUOTE_DETAIL = "quote-detail"
}

/*
* Another way to define routes
* */
sealed class Screen(val route: String) {
    data object Login : Screen(NavigationRoutes.LOGIN)
    data object Dashboard : Screen(NavigationRoutes.DASHBOARD)
    data object ChangeTheme : Screen(NavigationRoutes.CHANGE_THEME)
    data object QuoteList : Screen(NavigationRoutes.QUOTE_LIST)
    data object QuoteDetail : Screen(NavigationRoutes.QUOTE_DETAIL)
    data object TweetCategory : Screen(NavigationRoutes.TWEET_CATEGORY)
    data object TweetDetails : Screen(NavigationRoutes.TWEET_DETAILS)
    data object UIComponents : Screen(NavigationRoutes.UI_COMPONENTS)
}