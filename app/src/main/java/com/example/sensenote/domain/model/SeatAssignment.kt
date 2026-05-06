package com.example.sensenote.domain.model

data class SeatAssignment(
    val studentId: String,
    val x: Int,
    val y: Int,
    val isSafe: Boolean
)