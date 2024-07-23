package com.example.githubuserbrowser.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.githubuserbrowser.core.data.di.PagerModule.provideUserPager
import com.example.githubuserbrowser.core.data.model.UserModel
import com.example.githubuserbrowser.core.data.repository.OfflineFirstUserRepository
import com.example.githubuserbrowser.core.data.repository.UserRepository
import com.example.githubuserbrowser.core.database.GubDatabase
import com.example.githubuserbrowser.core.database.dao.UserDao
import com.example.githubuserbrowser.core.database.model.UserEntity
import com.example.githubuserbrowser.core.network.NetworkUserDataSource
import com.example.githubuserbrowser.core.network.dto.UserDto
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
class RepoUnitTest {

    private var userRepository: UserRepository? = null

    private var userDao: UserDao? = null

    private var networkUserDataSource: NetworkUserDataSource? = null

    private var database : GubDatabase? = null

    @Before
    fun setUp() {
        networkUserDataSource = mock(NetworkUserDataSource::class.java)
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, GubDatabase::class.java).allowMainThreadQueries().build()
        userDao = database?.userDao
        userRepository = OfflineFirstUserRepository(provideUserPager(networkUserDataSource!!, database!!),
            userDao!!, networkUserDataSource!!)
    }

    @After
    fun tearDown(): Unit = runBlocking {
        userDao?.clearAll()
    }

    @Test
    fun getUserDetail_fromNetwork_isCorrect(): Unit = runBlocking {
        `when`(networkUserDataSource!!.getUserDetail("abc")).thenReturn(UserDto(0,"abc", "asd", "asd"))
        val userModel = UserEntity(0,"abc", "asd", "asd", "", 0, 0, false)
        userDao?.upsertUsers(listOf(userModel))
        val mockUserDetail = userRepository?.getUserDetail("abc")
        mockUserDetail?.collect {
            assertEquals("abc", it.userName)
            assertEquals("asd", it.avatarUrl)
            assertEquals("asd", it.landingPageUrl)
            assertEquals("", it.location)
            assertEquals(0, it.followers)
            assertEquals(0, it.following)
        }
    }

    @Test
    fun getUserDetail_fromLocal_isCorrect(): Unit = runBlocking {
        val userModel = UserEntity(0,"abc", "asd", "asd", "", 0, 0, true)
        userDao?.upsertUsers(listOf(userModel))
        val mockUserDetail = userRepository?.getUserDetail("abc")
        mockUserDetail?.collect {
            assertEquals("abc", it.userName)
            assertEquals("asd", it.avatarUrl)
            assertEquals("asd", it.landingPageUrl)
            assertEquals("", it.location)
            assertEquals(0, it.followers)
            assertEquals(0, it.following)
        }
    }
}