package com.example.githubuserbrowser.mapper

import com.example.githubuserbrowser.core.data.repository.UserMapper
import com.example.githubuserbrowser.core.network.dto.UserDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class MapperUnitTest {

    private var mapper = UserMapper()

    @Test
    fun mapper_userDto_to_userEntity_isCorrect(){
        val userDto = UserDto(0,"hai123", "https://github.com/hai123.png", "https://github.com/hai123", "m", "hai",0 ,0)
        val userEntity = mapper.toUserEntity(userDto)
        assertEquals(userDto.id, userEntity.id)
        assertEquals(userDto.userName, userEntity.userName)
        assertEquals(userDto.avatarUrl, userEntity.avatarUrl)
        assertEquals(userDto.fullName, userEntity.fullName)
        assertEquals(userDto.landingPageUrl, userEntity.landingPageUrl)
        assertEquals(userDto.location, userEntity.location)
        assertEquals(userDto.followers, userEntity.followers)
        assertEquals(userDto.following, userEntity.following)
        assertFalse(userEntity.isLoadedDetail)
    }

    @Test
    fun mapper_userDto_to_userModel_isCorrect(){
        val userDto = UserDto(0,"hai123", "https://github.com/hai123.png", "https://github.com/hai123", "m", "hai",0 ,0)
        val userModel = mapper.asExternalModel(userDto)
        assertEquals(userDto.userName, userModel.userName)
        assertEquals(userDto.avatarUrl, userModel.avatarUrl)
        assertEquals(userDto.fullName, userModel.fullName)
        assertEquals(userDto.landingPageUrl, userModel.landingPageUrl)
        assertEquals(null, userModel.location)
        assertEquals(null, userModel.followers)
        assertEquals(null, userModel.following)

    }
}