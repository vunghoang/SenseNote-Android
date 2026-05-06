package com.example.sensenote.domain.repository

import com.example.sensenote.domain.model.BehaviorLog
import com.example.sensenote.domain.model.BehaviorCategory
import kotlinx.coroutines.flow.Flow

interface BehaviorRepository {
    suspend fun logBehavior(log: BehaviorLog): Result<Unit>
    fun getBehaviorHistory(studentId: String): Flow<List<BehaviorLog>>
    suspend fun getBehaviorCategories(teacherId: String): Result<List<BehaviorCategory>>
    suspend fun addCustomCategory(category: BehaviorCategory): Result<Unit>
}