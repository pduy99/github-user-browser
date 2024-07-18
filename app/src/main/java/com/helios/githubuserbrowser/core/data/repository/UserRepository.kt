package com.helios.githubuserbrowser.core.data.repository

import androidx.paging.PagingData
import com.helios.githubuserbrowser.core.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserList(): Flow<PagingData<UserModel>>

    fun getUserDetail(userName: String): Flow<UserModel>
}