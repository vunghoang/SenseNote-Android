package com.example.sensenote.data.repository

import com.example.sensenote.data.mapper.toRequest
import com.example.sensenote.data.remote.ApiService
import com.example.sensenote.domain.model.BehaviorLog
import com.example.sensenote.domain.model.BehaviorCategory
import com.example.sensenote.domain.repository.BehaviorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BehaviorRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BehaviorRepository {

    override suspend fun logBehavior(log: BehaviorLog): Result<Unit> {
        return try {
            val response = apiService.logBehavior(log.toRequest())
            if (response.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Không thể ghi nhận hành vi"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getBehaviorHistory(studentId: String): Flow<List<BehaviorLog>> = flow {
        emit(emptyList())
    }

    override suspend fun getBehaviorCategories(teacherId: String): Result<List<BehaviorCategory>> {
        return Result.success(emptyList())
    }

    override suspend fun addCustomCategory(category: BehaviorCategory): Result<Unit> = Result.success(Unit)
}