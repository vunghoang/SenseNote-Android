package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.*

interface BehaviorLogRepository {
    suspend fun logBehavior(request: LogBehaviorRequest): Result<Int>
    suspend fun getStudentHistory(studentId: Int): Result<StudentBehaviorLogHistoryVm>
    suspend fun getClassHistory(teachingContextId: Int): Result<ClassBehaviorLogHistoryVm>
}