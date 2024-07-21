package com.helios.githubuserbrowser.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.helios.githubuserbrowser.feature.home.navigation.HomeDestination
import com.helios.githubuserbrowser.feature.home.navigation.homeScreen

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeDestination) {
        homeScreen(
            onUserClick = {

            }
        )
    }
}