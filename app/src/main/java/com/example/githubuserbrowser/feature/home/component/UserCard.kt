package com.example.githubuserbrowser.feature.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubuserbrowser.R
import com.example.githubuserbrowser.core.data.model.UserModel

@Composable
fun UserCard(user: UserModel, onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }) {
                onClick(user.userName)
            },
        shape = RoundedCornerShape(CornerSize(5.dp)),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.user_avatar),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(text = user.userName, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.landingPageUrl)
            }
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    UserCard(
        user = UserModel(
            userName = "helios",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            landingPageUrl = "https://github.com/mojombo",
        ),
        onClick = {}
    )
}