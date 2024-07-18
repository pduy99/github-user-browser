package com.helios.githubuserbrowser.core.data.di

import com.helios.githubuserbrowser.core.data.repository.OfflineFirstUserRepository
import com.helios.githubuserbrowser.core.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindUserRepository(
        userRepositoryImpl: OfflineFirstUserRepository
    ): UserRepository
}