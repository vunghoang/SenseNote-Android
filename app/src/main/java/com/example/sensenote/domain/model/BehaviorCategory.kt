package com.example.sensenote.domain.model

data class BehaviorCategory(
    val id: String,
    val name: String,
    val type: CategoryType,
    val priority: Int,
    val colorCode: String?
)

enum class CategoryType {
    ANTECEDENT, BEHAVIOR, CONSEQUENCE
}