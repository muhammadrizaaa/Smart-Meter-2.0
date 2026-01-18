package org.riza0004.smartmeter20.navigation

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object AuthScreen: Screen("auth_screen")
}