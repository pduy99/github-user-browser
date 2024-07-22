package com.example.githubuserbrowser.core.network

import com.example.githubuserbrowser.core.network.dto.UserDto

interface NetworkUserDataSource {

    suspend fun getListUser(page: Int, perPage: Int): List<UserDto>

    suspend fun getUserDetail(userName: String): UserDto
}