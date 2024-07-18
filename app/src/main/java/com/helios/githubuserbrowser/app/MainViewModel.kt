package com.helios.githubuserbrowser.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.helios.githubuserbrowser.core.data.model.UserModel
import com.helios.githubuserbrowser.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {

    val userPagingDataFlow: Flow<PagingData<UserModel>> = userRepository.getUserList()
        .cachedIn(viewModelScope)
}