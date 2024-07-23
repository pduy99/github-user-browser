package com.example.githubuserbrowser.core.designsystem.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubuserbrowser.core.data.model.UserModel

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