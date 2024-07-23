package com.example.githubuserbrowser.core.data.model

import com.example.githubuserbrowser.core.database.model.UserEntity
import com.example.githubuserbrowser.core.network.dto.UserDto

data class UserModel(
    val userName: String,
    val avatarUrl: String,
    val landingPageUrl: String,
    val location: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
)

fun UserDto.toUserEntity() = UserEntity(
    id = id,
    userName = userName,
    avatarUrl = avatarUrl,
    landingPageUrl = landingPageUrl,
    location = location ?: "",
    followers = followers ?: 0,
    following = following ?: 0,
    isLoadedDetail = false,
)