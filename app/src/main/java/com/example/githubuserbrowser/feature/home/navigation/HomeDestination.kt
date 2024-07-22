package com.example.githubuserbrowser.feature.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubuserbrowser.feature.home.HomeScreen
import com.example.githubuserbrowser.feature.home.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeDestination

fun NavGraphBuilder.homeScreen(
    onUserClick: (String) -> Unit,
) {

    composable<HomeDestination> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val users = viewModel.userPagingDataFlow.collectAsLazyPagingItems()

        HomeScreen(
            userPagingItem = users,
            onUserClick = onUserClick
        )
    }
}