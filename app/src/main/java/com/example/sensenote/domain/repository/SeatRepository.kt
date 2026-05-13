package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.SeatAssignmentDto
import com.example.sensenote.data.remote.dto.UpdateSeatAssignmentRequest

interface SeatRepository {
    suspend fun getSeatAssignments(teachingContextId: Int): Result<List<SeatAssignmentDto>>

    suspend fun updateSeatAssignment(request: UpdateSeatAssignmentRequest): Result<Unit>
}