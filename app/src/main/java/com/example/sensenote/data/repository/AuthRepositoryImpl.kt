package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.ApiService
import com.example.sensenote.data.remote.dto.LoginRequest
import com.example.sensenote.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Đăng nhập thất bại: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(email: String, password: String): Result<Unit> {
        return try {
            val response = apiService.register(LoginRequest(email, password))
            if (response.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Đăng ký thất bại"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> = Result.success(Unit)

    override suspend fun refreshToken(): Result<String> = Result.success("")
}