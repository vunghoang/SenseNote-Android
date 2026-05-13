package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.TeachingContextApi
import com.example.sensenote.data.remote.dto.CreateTeachingContextRequest
import com.example.sensenote.data.remote.dto.TeachingContextDto
import com.example.sensenote.domain.repository.TeachingContextRepository
import javax.inject.Inject

class TeachingContextRepositoryImpl @Inject constructor(
    private val api: TeachingContextApi
) : TeachingContextRepository {

    override suspend fun getMyContexts(): Result<List<TeachingContextDto>> {
        return try {
            val response = api.getTeachingContexts()
            Result.success(response.items ?: emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createNewContext(request: CreateTeachingContextRequest): Result<Int> {
        return try {
            val id = api.createTeachingContext(request)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}