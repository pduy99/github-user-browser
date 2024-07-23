package com.example.githubuserbrowser.core.network.dto

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
    val landingPageUrl: String,
    @SerialName("location")
    val location: String? = null,
    @SerialName("name")
    val fullName: String? = null,
    @SerialName("followers")
    val followers: Int? = null,
    @SerialName("following")
    val following: Int? = null
)