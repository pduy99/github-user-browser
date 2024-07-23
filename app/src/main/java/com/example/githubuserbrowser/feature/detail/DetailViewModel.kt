package com.example.githubuserbrowser.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubuserbrowser.core.data.model.UserModel
import com.example.githubuserbrowser.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private var userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    fun getUserDetail(userName: String) {
        userRepository.getUserDetail(userName).onStart {
            _uiState.value = _uiState.value.copy(isLoading = true)
        }.catch {
            _uiState.value = _uiState.value.copy(isLoading = false)
        }.onEach { res ->
            _uiState.value = _uiState.value.copy(isLoading = false, userDetail = res)
        }.launchIn(viewModelScope)
    }
}

data class UIState(
    var isLoading: Boolean = false,
    var userDetail: UserModel? = null
)

