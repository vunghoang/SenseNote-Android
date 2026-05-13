package com.example.sensenote.domain.model

import androidx.compose.ui.graphics.Color

data class Seat(
    val id: Int,
    val studentName: String?,
    val x: Int,
    val y: Int,
    val isSelected: Boolean = false
)