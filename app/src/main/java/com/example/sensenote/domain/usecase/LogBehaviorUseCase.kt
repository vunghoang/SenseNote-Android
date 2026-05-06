package com.example.sensenote.domain.usecase

import com.example.sensenote.domain.model.BehaviorLog
import com.example.sensenote.domain.repository.BehaviorRepository
import javax.inject.Inject

class LogBehaviorUseCase @Inject constructor(
    private val repository: BehaviorRepository
) {
    suspend operator fun invoke(log: BehaviorLog): Result<Unit> {
        if (log.antecedent.isBlank() || log.behavior.isBlank() || log.consequence.isBlank()) {
            return Result.failure(Exception("Phải chọn đầy đủ Tiền đề, Hành vi và Hệ quả."))
        }
        return repository.logBehavior(log)
    }
}