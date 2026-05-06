package com.example.sensenote.domain.model

data class ClassItem(
    val id: String,
    val className: String,
    val studentCount: Int,
    val hasWarning: Boolean = false,
    val rows: Int,
    val cols: Int,
)