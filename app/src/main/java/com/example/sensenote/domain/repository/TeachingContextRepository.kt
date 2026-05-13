package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.CreateTeachingContextRequest
import com.example.sensenote.data.remote.dto.TeachingContextDto

interface TeachingContextRepository {
    suspend fun getMyContexts(): Result<List<TeachingContextDto>>

    suspend fun createNewContext(request: CreateTeachingContextRequest): Result<Int>
}