package com.example.githubuserbrowser.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githubuserbrowser.core.data.repository.UserMapper
import com.example.githubuserbrowser.core.data.repository.paging.UserRemoteMediator
import com.example.githubuserbrowser.core.database.GubDatabase
import com.example.githubuserbrowser.core.database.model.UserEntity
import com.example.githubuserbrowser.core.network.NetworkUserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PagerModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideUserPager(
        network: NetworkUserDataSource,
        database: GubDatabase,
        userMapper: UserMapper
    ): Pager<Int, UserEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = UserRemoteMediator(
                network = network,
                database,
                mapper = userMapper
            ),
            pagingSourceFactory = {
                database.userDao.pagingSource()
            }
        )
    }
}