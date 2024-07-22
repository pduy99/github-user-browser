package com.example.githubuserbrowser.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.githubuserbrowser.core.data.model.UserModel
import com.example.githubuserbrowser.feature.home.component.UserCard

@Composable
fun HomeScreen(
    userPagingItem: LazyPagingItems<UserModel>,
    onUserClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (userPagingItem.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = userPagingItem.itemCount,
                    key = userPagingItem.itemKey { it.userName },
                ) { index ->
                    val user = userPagingItem[index]
                    if (user != null) {
                        UserCard(
                            user,
                            onClick = onUserClick,
                            modifier = Modifier.fillMaxWidth().height(120.dp))
                    }
                }
                item {
                    if (userPagingItem.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}