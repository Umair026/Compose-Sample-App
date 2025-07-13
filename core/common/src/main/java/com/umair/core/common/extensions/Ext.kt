package com.umair.core.common.extensions

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun String.Companion.empty () : String = String()

fun NavController.safeNavigate(
    route: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    try {
        this.navigate(route, builder)
    } catch (e: IllegalArgumentException) {
        Log.e("SafeNav", "Navigation failed. Route not found: $route")
    } catch (e: Exception) {
        Log.e("SafeNav", "Navigation error: ${e.localizedMessage}")
    }
}