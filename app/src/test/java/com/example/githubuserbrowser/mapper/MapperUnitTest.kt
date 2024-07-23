package com.example.githubuserbrowser.mapper

import com.example.githubuserbrowser.core.data.model.toUserEntity
import com.example.githubuserbrowser.core.network.dto.UserDto
import com.example.githubuserbrowser.core.network.dto.asExternalModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class MapperUnitTest {

    @Test
    fun mapper_userDto_to_userEntity_isCorrect(){
        val userDto = UserDto(0,"hai123", "https://github.com/hai123.png", "https://github.com/hai123")
        val userEntity = userDto.toUserEntity()
        assertEquals(userDto.id, userEntity.id)
        assertEquals(userDto.userName, userEntity.userName)
        assertEquals(userDto.avatarUrl, userEntity.avatarUrl)
        assertEquals(userDto.landingPageUrl, userEntity.landingPageUrl)
        assertEquals("", userEntity.location)
        assertEquals(0, userEntity.followers)
        assertEquals(0, userEntity.following)
        assertFalse(userEntity.isLoadedDetail)
    }

    @Test
    fun mapper_userDto_to_userModel_isCorrect(){
        val userDto = UserDto(0,"hai123", "https://github.com/hai123.png", "https://github.com/hai123")
        val userModel = userDto.asExternalModel()
        assertEquals(userDto.userName, userModel.userName)
        assertEquals(userDto.avatarUrl, userModel.avatarUrl)
        assertEquals(userDto.landingPageUrl, userModel.landingPageUrl)
        assertEquals(null, userModel.location)
        assertEquals(null, userModel.followers)
        assertEquals(null, userModel.following)

    }
}