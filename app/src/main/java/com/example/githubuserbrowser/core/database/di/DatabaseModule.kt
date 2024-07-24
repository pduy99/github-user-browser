package com.example.githubuserbrowser.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.githubuserbrowser.core.database.GubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideGubDatabase(
        @ApplicationContext context: Context,
    ): GubDatabase = Room.databaseBuilder(
        context,
        GubDatabase::class.java,
        "gub-database",
    ).fallbackToDestructiveMigration().build()
}