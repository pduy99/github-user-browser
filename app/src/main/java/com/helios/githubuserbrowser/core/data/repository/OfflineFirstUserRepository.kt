package com.helios.githubuserbrowser.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.helios.githubuserbrowser.core.data.model.UserModel
import com.helios.githubuserbrowser.core.database.model.UserEntity
import com.helios.githubuserbrowser.core.database.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstUserRepository @Inject constructor(
    private val userPager: Pager<Int, UserEntity>
) : UserRepository {

    override fun getUserList(): Flow<PagingData<UserModel>> {
        return userPager.flow.map { pagingData ->
            pagingData.map { it.asExternalModel() }
        }
    }

    override fun getUserDetail(userName: String): Flow<UserModel> = flow {
        // Not implemented
    }
}