package com.example.githubuserbrowser.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.githubuserbrowser.feature.home.navigation.HomeDestination
import com.example.githubuserbrowser.feature.home.navigation.homeScreen

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeDestination) {
        homeScreen(
            onUserClick = {

            }
        )
    }
}