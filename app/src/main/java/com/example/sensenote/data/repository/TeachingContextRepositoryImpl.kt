package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.ApiService
import com.example.sensenote.domain.model.TeachingContext
import com.example.sensenote.domain.model.SeatAssignment
import com.example.sensenote.domain.repository.TeachingContextRepository
import javax.inject.Inject

class TeachingContextRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TeachingContextRepository {

    override suspend fun getTeachingContext(contextId: String): Result<TeachingContext> {
        return Result.failure(Exception("Chưa thực thi"))
    }

    override suspend fun updateSeatAssignment(contextId: String, assignments: List<SeatAssignment>): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun getSeatAssignments(contextId: String): Result<List<SeatAssignment>> {
        return Result.success(emptyList())
    }
}