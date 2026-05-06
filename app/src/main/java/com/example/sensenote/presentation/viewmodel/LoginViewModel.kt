package com.example.sensenote.presentation.viewmodel

import androidx.compose.runtime.getValue // Thêm dòng này
import androidx.compose.runtime.setValue // Thêm dòng này
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }

//    fun onLoginClick(onSuccess: () -> Unit) {
//        viewModelScope.launch {
//            uiState = uiState.copy(isLoading = true, error = null)
//
//            val result = authRepository.login(uiState.email, uiState.password)
//
//            if (result.isSuccess) {
//                onSuccess()
//            } else {
//                uiState = uiState.copy(error = "Sai tài khoản hoặc mật khẩu")
//            }
//            uiState = uiState.copy(isLoading = false)
//        }
//    }
    fun onLoginClick(onSuccess: () -> Unit) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            kotlinx.coroutines.delay(1000)

            uiState = uiState.copy(isLoading = false)
            onSuccess()
        }
    }
}