package com.helios.githubuserbrowser.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.helios.githubuserbrowser.core.data.model.UserModel

@Entity(
    tableName = "users"
)
data class UserEntity(
    @PrimaryKey
    val userName: String,
    val avatarUrl: String,
    val landingPageUrl: String,
    @ColumnInfo(defaultValue = "")
    val location: String,
    val followers: Int,
    val following: Int
)

fun UserEntity.asExternalModel() = UserModel(
    userName = userName,
    avatarUrl = avatarUrl,
    landingPageUrl = landingPageUrl,
    location = location,
    followers = followers,
    following = following
)