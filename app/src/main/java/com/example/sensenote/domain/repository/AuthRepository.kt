package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.*

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse>
    suspend fun register(email: String, password: String, fullName: String): Result<RegisterResponse>
    suspend fun logout(): Result<Unit>

    suspend fun refreshToken(token: String): Result<LoginResponse>
}