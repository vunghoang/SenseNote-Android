package com.example.sensenote.domain.usecase

import com.example.sensenote.domain.model.SeatAssignment
import com.example.sensenote.domain.repository.TeachingContextRepository
import javax.inject.Inject

class UpdateSeatAssignmentUseCase @Inject constructor(
    private val repository: TeachingContextRepository
) {
    suspend operator fun invoke(contextId: String, assignments: List<SeatAssignment>): Result<Unit> {
        return repository.updateSeatAssignment(contextId, assignments)
    }
}