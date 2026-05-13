package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.*

interface BehaviorCategoryRepository {
    suspend fun createCategory(request: CreateBehaviorCategoryRequest): Result<Int>
    suspend fun updateCategory(request: UpdateBehaviorCategoryRequest): Result<Unit>
    suspend fun deleteCategory(id: Int): Result<Unit>
    suspend fun applyToContext(request: BehaviorCategoryMappingRequest): Result<Unit>
    suspend fun removeFromContext(request: BehaviorCategoryMappingRequest): Result<Unit>
    suspend fun getCategoriesByTeacher(): Result<List<BehaviorCategoryDto>>
    suspend fun getCategoriesByContext(contextId: Int): Result<List<BehaviorCategoryDto>>
}