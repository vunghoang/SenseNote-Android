package com.example.sensenote.domain.model

import java.time.LocalDateTime

data class BehaviorLog(
    val id: String,
    val studentId: String,
    val antecedent: String,
    val behavior: String,
    val consequence: String,
    val timestamp: LocalDateTime,
    val locationContext: String?
)