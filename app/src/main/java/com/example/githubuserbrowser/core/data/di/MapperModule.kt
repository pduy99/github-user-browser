package com.example.githubuserbrowser.core.data.di

import com.example.githubuserbrowser.core.data.repository.UserMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun providesModelMapper(): UserMapper = UserMapper()
}