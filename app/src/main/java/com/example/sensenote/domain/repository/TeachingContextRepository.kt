package com.example.sensenote.domain.repository

import com.example.sensenote.domain.model.TeachingContext
import com.example.sensenote.domain.model.SeatAssignment

interface TeachingContextRepository {
    suspend fun getTeachingContext(contextId: String): Result<TeachingContext>
    suspend fun updateSeatAssignment(contextId: String, assignments: List<SeatAssignment>): Result<Unit>
    suspend fun getSeatAssignments(contextId: String): Result<List<SeatAssignment>>
}