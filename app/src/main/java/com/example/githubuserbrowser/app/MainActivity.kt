package com.example.githubuserbrowser.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.githubuserbrowser.app.navigation.MainNavigation
import com.example.githubuserbrowser.core.designsystem.theme.GitHubUserBrowserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubUserBrowserTheme {
                MainNavigation()
            }
        }
    }
}