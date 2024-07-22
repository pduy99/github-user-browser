package com.example.githubuserbrowser.core.network.dto

import com.example.githubuserbrowser.core.data.model.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int,
    @SerialName("login")
    val userName: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val landingPageUrl: String
)

fun UserDto.asExternalModel() = UserModel(
    userName = userName,
    avatarUrl = avatarUrl,
    landingPageUrl = landingPageUrl
)