package com.example.githubuserbrowser.feature.home.component

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubuserbrowser.R
import com.example.githubuserbrowser.core.data.model.UserModel
import com.example.githubuserbrowser.feature.detail.components.AvatarImageView

@Composable
fun UserCard(user: UserModel, onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
        onClick = { onClick(user.userName) },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF),
        )) {
        AvatarImageView(modifier = Modifier.fillMaxWidth(), urlPath = user.avatarUrl, "")
        Text(
            text = user.userName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 2.dp),
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.landingPageUrl,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 2.dp)
        )
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