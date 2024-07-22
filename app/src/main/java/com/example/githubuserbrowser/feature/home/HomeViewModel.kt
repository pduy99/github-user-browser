package com.example.githubuserbrowser.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubuserbrowser.core.data.model.UserModel
import com.example.githubuserbrowser.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    userRepository: UserRepository,
) : ViewModel() {

    val userPagingDataFlow: Flow<PagingData<UserModel>> = userRepository.getUserList()
        .cachedIn(viewModelScope)
}