package com.example.githubuserbrowser.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.githubuserbrowser.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query(
        value = """
        SELECT * FROM users
        WHERE userName = :userName
    """,
    )
    fun getUserEntity(userName: String): Flow<UserEntity>

    /**
     * Inserts or updates [entities] in the db under the specified primary keys
     */
    @Upsert
    suspend fun upsertUsers(entities: List<UserEntity>)

    /**
     * Inserts [userEntities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTopics(userEntities: List<UserEntity>): List<Long>

    @Query("SELECT * FROM users")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}