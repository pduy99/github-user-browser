package com.helios.githubuserbrowser.core.network

import com.helios.githubuserbrowser.core.network.dto.UserDto

interface NetworkUserDataSource {

    suspend fun getListUser(page: Int, perPage: Int): List<UserDto>

    suspend fun getUserDetail(userName: String): UserDto
}