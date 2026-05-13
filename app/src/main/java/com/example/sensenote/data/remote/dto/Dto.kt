package com.example.sensenote.data.remote.dto

data class ApiResponse<T>(
    val code: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)

data class PageResponse<T>(
    val content: List<T>,
    val totalPages: Int,
    val totalElements: Long,
    val number: Int,
    val size: Int
)

fun <T, R> PageResponse<T>.mapContent(
    transform: (T) -> R
): PageResponse<R> {
    return PageResponse(
        content = content.map(transform),
        totalPages = totalPages,
        totalElements = totalElements,
        size = size,
        number = number
    )
}