package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.data.local.TokenManager
import com.example.sensenote.data.remote.dto.LoginResponse
import com.example.sensenote.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val data: LoginResponse) : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<AuthState>(AuthState.Idle)
    val loginState = _loginState.asStateFlow()

    // ... trong LoginViewModel
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = AuthState.Loading
            val result = authRepository.login(email, password)

            result.onSuccess { response ->
                // Lưu token vào máy ngay khi login thành công
                tokenManager.saveToken(response.accessToken)
                _loginState.value = AuthState.Success(response)
            }.onFailure { /* ... */ }
        }
    }

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            _loginState.value = AuthState.Loading
            val result = authRepository.register(email, password, fullName)
            result.onSuccess { response ->
                if (response.accessToken != null) {
                    _loginState.value = AuthState.Success(
                        LoginResponse(response.accessToken, response.refreshToken ?: "")
                    )
                } else {
                    login(email, password)
                }
            }.onFailure { exception ->
                _loginState.value = AuthState.Error(exception.message ?: "Đăng ký thất bại")
            }
        }
    }
}