package com.example.sensenote.domain.repository

import com.example.sensenote.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(email: String, password: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun refreshToken(): Result<String>
}