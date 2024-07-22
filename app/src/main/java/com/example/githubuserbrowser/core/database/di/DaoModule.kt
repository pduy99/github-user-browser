package com.example.githubuserbrowser.core.database.di

import com.example.githubuserbrowser.core.database.GubDatabase
import com.example.githubuserbrowser.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun providesUserDao(
        database: GubDatabase,
    ): UserDao = database.userDao
}