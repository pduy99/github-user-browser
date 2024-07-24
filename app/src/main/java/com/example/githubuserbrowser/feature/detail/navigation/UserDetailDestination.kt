package com.example.githubuserbrowser.feature.detail.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.githubuserbrowser.feature.detail.DetailViewModel
import com.example.githubuserbrowser.feature.detail.UserDetailScreen
import kotlinx.serialization.Serializable

@Serializable
class UserDetailDestination(val userName: String)

fun NavGraphBuilder.userDetailScreen(
    onBackPressed: () -> Unit,
) {

    composable<UserDetailDestination> {
        val viewModel = hiltViewModel<DetailViewModel>()
        it.arguments?.getString("userName")?.let { userName ->
            LaunchedEffect(userName) {
                viewModel.getUserDetail(userName)
            }
        }
        val uiState = viewModel.uiState.collectAsState().value
        UserDetailScreen(
            uiState,
            onBackPressed = {
                viewModel.clearUiState()
                onBackPressed()
            }
        )
    }
}

