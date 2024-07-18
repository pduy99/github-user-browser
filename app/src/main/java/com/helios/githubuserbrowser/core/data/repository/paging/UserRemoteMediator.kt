package com.helios.githubuserbrowser.core.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.helios.githubuserbrowser.core.data.model.toUserEntity
import com.helios.githubuserbrowser.core.database.GubDatabase
import com.helios.githubuserbrowser.core.database.model.UserEntity
import com.helios.githubuserbrowser.core.network.NetworkUserDataSource
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class UserRemoteMediator @Inject constructor(
    private val network: NetworkUserDataSource,
    private val database: GubDatabase
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        (state.pages.last().data.size / state.config.pageSize) + 1
                    }
                }
            }

            val users = network.getListUser(offset, state.config.pageSize)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userDao.clearAll()
                }

                database.userDao.upsertTopics(users.map { it.toUserEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = users.isEmpty())
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}