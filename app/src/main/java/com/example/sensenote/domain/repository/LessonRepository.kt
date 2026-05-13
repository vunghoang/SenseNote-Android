package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.LessonDto
import com.example.sensenote.data.remote.dto.StartLessonRequest

interface LessonRepository {
    suspend fun getLessons(teachingContextId: Int): Result<List<LessonDto>>

    suspend fun startLesson(teachingContextId: Int): Result<Int>

    suspend fun endLesson(lessonId: Int): Result<Unit>
}