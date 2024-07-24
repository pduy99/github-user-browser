package com.example.githubuserbrowser.core.data.repository

import com.example.githubuserbrowser.core.data.model.UserModel
import com.example.githubuserbrowser.core.database.model.UserEntity
import com.example.githubuserbrowser.core.network.dto.UserDto

class UserMapper {

    fun asExternalModel(entity: UserEntity) = UserModel(
        userName = entity.userName,
        avatarUrl = entity.avatarUrl,
        landingPageUrl = entity.landingPageUrl,
        location = entity.location,
        followers = entity.followers,
        fullName = entity.fullName,
        following = entity.following,
        publicRepo = entity.publicRepo
    )

    fun toUserEntity(dto: UserDto) = UserEntity(
        id = dto.id,
        userName = dto.userName,
        avatarUrl = dto.avatarUrl,
        landingPageUrl = dto.landingPageUrl,
        fullName = dto.fullName ?: "",
        location = dto.location ?: "",
        followers = dto.followers ?: 0,
        following = dto.following ?: 0,
        publicRepo = dto.publicRepo ?: 0,
        isLoadedDetail = false,
    )



    fun asExternalModel(dto: UserDto) = UserModel(
        userName = dto.userName,
        avatarUrl = dto.avatarUrl,
        landingPageUrl = dto.landingPageUrl,
        location = dto.location,
        following = dto.following,
        followers = dto.followers,
        fullName = dto.fullName,
        publicRepo = dto.publicRepo
    )

}