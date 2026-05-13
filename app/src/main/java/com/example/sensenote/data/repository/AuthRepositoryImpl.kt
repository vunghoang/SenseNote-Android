package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.AuthApi
import com.example.sensenote.data.remote.apiCall // Import hàm tiện ích từ Api.kt
import com.example.sensenote.data.remote.dto.*
import com.example.sensenote.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            // Sử dụng apiCall để tự động kiểm tra success và unwrap data
            val response = api.login(LoginRequest(email, password))
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(email: String, password: String, fullName: String): Result<RegisterResponse> {
        return try {
            val data = api.register(RegisterRequest(email, password, fullName))
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            apiCall { api.logout() }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun refreshToken(token: String): Result<LoginResponse> {
        return try {
            val data = apiCall { api.refreshToken(RefreshTokenRequest(token)) }
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}