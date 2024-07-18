package com.helios.githubuserbrowser.core.network.retrofit

import com.helios.githubuserbrowser.BuildConfig.BASE_URL
import com.helios.githubuserbrowser.core.network.NetworkUserDataSource
import com.helios.githubuserbrowser.core.network.dto.UserDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitApi {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int, @Query("per_page") perPage: Int): List<UserDto>

    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") username: String): UserDto
}

@Singleton
internal class RetrofitUserDataSource @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : NetworkUserDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        // We use callFactory lambda here with dagger.Lazy<Call.Factory>
        // to prevent initializing OkHttp on the main thread.
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitApi::class.java)

    override suspend fun getListUser(page: Int, perPage: Int): List<UserDto> {
        return networkApi.getUsers(page, perPage)
    }

    override suspend fun getUserDetail(userName: String): UserDto {
        return networkApi.getUser(userName)
    }
}