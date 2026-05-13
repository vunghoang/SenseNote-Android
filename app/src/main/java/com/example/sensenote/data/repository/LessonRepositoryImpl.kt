package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.LessonApi
import com.example.sensenote.data.remote.dto.LessonDto
import com.example.sensenote.data.remote.dto.StartLessonRequest
import com.example.sensenote.domain.repository.LessonRepository
import javax.inject.Inject

class LessonRepositoryImpl @Inject constructor(
    private val api: LessonApi
) : LessonRepository {

    override suspend fun getLessons(teachingContextId: Int): Result<List<LessonDto>> {
        return try {
            val response = api.getLessonsByContext(teachingContextId)
            Result.success(response.items)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun startLesson(teachingContextId: Int): Result<Int> {
        return try {
            val lessonId = api.startLesson(StartLessonRequest(teachingContextId))
            Result.success(lessonId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun endLesson(lessonId: Int): Result<Unit> {
        return try {
            api.endLesson(lessonId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}