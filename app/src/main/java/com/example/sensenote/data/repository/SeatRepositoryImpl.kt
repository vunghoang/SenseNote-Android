package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.SeatAssignmentApi
import com.example.sensenote.data.remote.dto.SeatAssignmentDto
import com.example.sensenote.data.remote.dto.UpdateSeatAssignmentRequest
import com.example.sensenote.domain.repository.SeatRepository
import javax.inject.Inject

class SeatRepositoryImpl @Inject constructor(
    private val api: SeatAssignmentApi
) : SeatRepository {

    override suspend fun getSeatAssignments(teachingContextId: Int): Result<List<SeatAssignmentDto>> {
        return try {
            val response = api.getSeatAssignments(teachingContextId)
            Result.success(response.seatAssignments)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateSeatAssignment(request: UpdateSeatAssignmentRequest): Result<Unit> {
        return try {
            api.updateSeatAssignment(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}