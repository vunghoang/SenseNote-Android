package com.example.sensenote.data.remote.dto

data class LogBehaviorRequest(
    val studentId: Int,
    val behaviorCategoryId: Int,
    val lessonId: Int,
    val note: String? = null
)

data class BehaviorLogDto(
    val id: Int,
    val studentId: Int,
    val studentName: String?,
    val behaviorCategoryName: String,
    val point: Int,
    val note: String?,
    val createdAt: String
)

data class StudentBehaviorLogHistoryVm(
    val studentId: Int,
    val studentName: String,
    val logs: List<BehaviorLogDto>
)

data class ClassBehaviorLogHistoryVm(
    val teachingContextId: Int,
    val logs: List<BehaviorLogDto>
)