package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.BehaviorLogApi
import com.example.sensenote.data.remote.dto.*
import com.example.sensenote.domain.repository.BehaviorLogRepository
import javax.inject.Inject

class BehaviorLogRepositoryImpl @Inject constructor(
    private val api: BehaviorLogApi
) : BehaviorLogRepository {

    override suspend fun logBehavior(request: LogBehaviorRequest) = try {
        Result.success(api.logBehavior(request))
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun getStudentHistory(studentId: Int) = try {
        Result.success(api.getStudentHistory(studentId))
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun getClassHistory(teachingContextId: Int) = try {
        Result.success(api.getClassHistory(teachingContextId))
    } catch (e: Exception) { Result.failure(e) }
}