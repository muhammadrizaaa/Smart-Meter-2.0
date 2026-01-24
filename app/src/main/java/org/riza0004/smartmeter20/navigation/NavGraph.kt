package org.riza0004.smartmeter20.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.riza0004.smartmeter20.ui.screen.homescreen.HomeScreen
import org.riza0004.smartmeter20.ui.screen.homescreen.MainViewModel
import org.riza0004.smartmeter20.ui.screen.profilescreen.ProfileScreen
import org.riza0004.smartmeter20.ui.screen.usagereportmain.UsageReportMainScreen

@Composable
fun SetupNavGraph(navHostController: NavHostController = rememberNavController()){
    val mainViewModel: MainViewModel = viewModel()
    val userFlow by mainViewModel.userFlow.collectAsState()
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(
                navHostController,
                userFlow
            )
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(navHostController)
        }
        composable(
            route = Screen.UsageReportMainScreen.route
        ){
            UsageReportMainScreen(
                navHostController = navHostController,
                userFlow = userFlow
            )
        }
    }
}