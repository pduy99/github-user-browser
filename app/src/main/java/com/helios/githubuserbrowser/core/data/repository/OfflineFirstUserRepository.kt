package com.helios.githubuserbrowser.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.helios.githubuserbrowser.core.data.model.UserModel
import com.helios.githubuserbrowser.core.data.model.toUserEntity
import com.helios.githubuserbrowser.core.database.dao.UserDao
import com.helios.githubuserbrowser.core.database.model.UserEntity
import com.helios.githubuserbrowser.core.database.model.asExternalModel
import com.helios.githubuserbrowser.core.network.NetworkUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstUserRepository @Inject constructor(
    private val userPager: Pager<Int, UserEntity>,
    private val userDao: UserDao,
    private val networkUserDataSource: NetworkUserDataSource
) : UserRepository {

    override fun getUserList(): Flow<PagingData<UserModel>> {
        return userPager.flow.map { pagingData ->
            pagingData.map { it.asExternalModel() }
        }
    }

    override fun getUserDetail(userName: String): Flow<UserModel> = flow {
        var savedUser = userDao.getUserEntity(userName).first()
        if (!savedUser.isLoadedDetail) {
            networkUserDataSource.getUserDetail(userName).run {
                savedUser = this.toUserEntity().copy(isLoadedDetail = true)
                userDao.upsertUsers(listOf(savedUser))
            }
        }
        emit(savedUser.asExternalModel())
    }
}