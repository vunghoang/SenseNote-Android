package com.example.sensenote.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BehaviorItem(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val category: BehaviorCategory,
    val iconName: String
)

enum class BehaviorCategory {
    STIMULUS,    // Nhóm A: Kích thích
    BEHAVIOR,    // Nhóm B: Hành vi
    INTERVENTION // Nhóm C: Xử lý
}