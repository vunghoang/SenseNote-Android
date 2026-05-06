package com.example.sensenote.domain.model

data class Student(
    val id: String,
    val name: String,
    val dob: String,
    val status: String,
    val isWarning: Boolean,
    val classroomId: String,
    val sensoryThresholds: Map<String, Int>
)
