package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.LessonVm
import com.example.sensenote.data.remote.dto.StartLessonRequest
import retrofit2.http.*

interface LessonApi {
    @GET("lessons/{teachingContextId}")
    suspend fun getLessonsByContext(
        @Path("teachingContextId") teachingContextId: Int
    ): LessonVm

    @POST("lessons")
    suspend fun startLesson(
        @Body request: StartLessonRequest
    ): Int

    @PUT("lessons/{lessonId}")
    suspend fun endLesson(
        @Path("lessonId") lessonId: Int
    )
}