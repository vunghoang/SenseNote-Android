package com.example.sensenote.data.remote.dto

data class LessonDto(
    val id: Int,
    val teachingContextId: Int,
    val startTime: String,
    val endTime: String?,
    val isActive: Boolean
)

data class LessonVm(
    val items: List<LessonDto>
)

data class StartLessonRequest(
    val teachingContextId: Int
)