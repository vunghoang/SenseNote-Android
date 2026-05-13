package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.data.remote.dto.LoginResponse
import com.example.sensenote.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Trạng thái riêng cho luồng đăng ký
sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val tokenData: LoginResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState = _registerState.asStateFlow()

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading

            // Gọi repository để đăng ký tài khoản mới
            val result = authRepository.register(email, password, fullName)

            result.onSuccess { response ->
                // Kiểm tra nếu backend trả về token luôn để thực hiện auto-login
                if (response.accessToken != null) {
                    _registerState.value = RegisterState.Success(
                        LoginResponse(response.accessToken, response.refreshToken ?: "")
                    )
                } else {
                    // Nếu không có token, gọi login ngầm để lấy token
                    val loginResult = authRepository.login(email, password)
                    loginResult.onSuccess { loginResponse ->
                        _registerState.value = RegisterState.Success(loginResponse)
                    }.onFailure {
                        _registerState.value = RegisterState.Error("Đăng ký thành công nhưng không thể tự động đăng nhập")
                    }
                }
            }.onFailure { exception ->
                _registerState.value = RegisterState.Error(exception.message ?: "Đăng ký thất bại")
            }
        }
    }
}