package org.riza0004.smartmeter20.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.riza0004.smartmeter20.navigation.Screen

@Composable
fun SetupNavGraph(navHostController: NavHostController = rememberNavController()){
    NavHost(
        navController = navHostController,
        startDestination = Screen.AuthScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navHostController)
        }
    }
}