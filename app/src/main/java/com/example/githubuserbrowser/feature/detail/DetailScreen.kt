package com.example.githubuserbrowser.feature.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubuserbrowser.R
import com.example.githubuserbrowser.core.designsystem.components.AvatarImageView
import com.example.githubuserbrowser.core.designsystem.components.HyperlinkText
import com.example.githubuserbrowser.core.designsystem.components.IndeterminateCircularIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(uiState: UIState, onBackPressed: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(uiState.errorText, uiState.userDetail?.userName) {
        if(uiState.errorText != null) {
            Toast.makeText(context, uiState.errorText.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable {
                                onBackPressed()
                            }
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(4.dp, contentPadding.calculateTopPadding(), 0.dp, contentPadding.calculateBottomPadding())
        ) {
            uiState.userDetail?.avatarUrl?.let {
                AvatarImageView(
                    Modifier
                        .height(250.dp)
                        .width(250.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally), it)
            }
            if (uiState.isLoading) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    IndeterminateCircularIndicator(
                        Modifier
                            .height(48.dp)
                            .width(48.dp)
                            .padding(8.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                uiState.userDetail?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it.fullName.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "@" + it.userName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (it.location?.isNotEmpty() == true) {
                        Row(modifier = Modifier) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_home),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(24.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = it.location,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 20.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_follower),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Followers |",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = it.followers.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_following),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Following |",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = it.following.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_repo),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Public Repo |",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = it.publicRepo.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    it.landingPageUrl.let { homepageUrl ->
                        val showText = "Look at their homepage: $homepageUrl"
                        HyperlinkText(fullText = showText, linkText = listOf(homepageUrl), hyperlinks = listOf(homepageUrl), fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}