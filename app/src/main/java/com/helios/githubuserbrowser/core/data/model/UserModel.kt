package com.helios.githubuserbrowser.core.data.model

import com.helios.githubuserbrowser.core.database.model.UserEntity
import com.helios.githubuserbrowser.core.network.dto.UserDto

data class UserModel(
    val userName: String,
    val avatarUrl: String,
    val landingPageUrl: String,
    val location: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
)

fun UserDto.toUserEntity() = UserEntity(
    userName = userName,
    avatarUrl = avatarUrl,
    landingPageUrl = landingPageUrl,
    location = "",
    followers = 0,
    following = 0
)