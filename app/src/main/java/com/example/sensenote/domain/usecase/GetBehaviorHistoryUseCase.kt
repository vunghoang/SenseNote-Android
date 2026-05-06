package com.example.sensenote.domain.usecase

import com.example.sensenote.domain.model.BehaviorLog
import com.example.sensenote.domain.repository.BehaviorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBehaviorHistoryUseCase @Inject constructor(
    private val repository: BehaviorRepository
) {
    operator fun invoke(studentId: String): Flow<List<BehaviorLog>> {
        return repository.getBehaviorHistory(studentId)
    }
}