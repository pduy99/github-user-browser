package com.example.githubuserbrowser.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubuserbrowser.core.database.dao.UserDao
import com.example.githubuserbrowser.core.database.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class GubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}