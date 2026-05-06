package com.example.sensenote.domain.model

import androidx.compose.ui.graphics.Color

data class Seat(
    val studentName: String,
    val isWarning: Boolean = false,
    val avatarColor: Color = Color(0xFFB2EBF2)
)