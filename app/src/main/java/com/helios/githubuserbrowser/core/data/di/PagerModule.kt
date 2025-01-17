package com.helios.githubuserbrowser.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.helios.githubuserbrowser.core.data.repository.paging.UserRemoteMediator
import com.helios.githubuserbrowser.core.database.GubDatabase
import com.helios.githubuserbrowser.core.database.model.UserEntity
import com.helios.githubuserbrowser.core.network.NetworkUserDataSource
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
        database: GubDatabase
    ): Pager<Int, UserEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = UserRemoteMediator(
                network = network,
                database
            ),
            pagingSourceFactory = {
                database.userDao.pagingSource()
            },
        )
    }
}