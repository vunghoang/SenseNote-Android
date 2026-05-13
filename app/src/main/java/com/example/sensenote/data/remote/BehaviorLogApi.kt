package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.*
import retrofit2.http.*

interface BehaviorLogApi {
    @POST("behavior-logs")
    suspend fun logBehavior(@Body request: LogBehaviorRequest): Int

    @GET("behavior-logs/student")
    suspend fun getStudentHistory(
        @Query("StudentId") studentId: Int
    ): StudentBehaviorLogHistoryVm

    @GET("behavior-logs/class")
    suspend fun getClassHistory(
        @Query("TeachingContextId") teachingContextId: Int
    ): ClassBehaviorLogHistoryVm
}