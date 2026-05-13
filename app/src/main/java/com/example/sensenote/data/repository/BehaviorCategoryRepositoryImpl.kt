package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.BehaviorCategoryApi
import com.example.sensenote.data.remote.dto.*
import com.example.sensenote.domain.repository.BehaviorCategoryRepository
import javax.inject.Inject

class BehaviorCategoryRepositoryImpl @Inject constructor(
    private val api: BehaviorCategoryApi
) : BehaviorCategoryRepository {

    override suspend fun createCategory(request: CreateBehaviorCategoryRequest) = try {
        Result.success(api.createCategory(request))
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun updateCategory(request: UpdateBehaviorCategoryRequest) = try {
        api.updateCategory(request)
        Result.success(Unit)
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun deleteCategory(id: Int) = try {
        api.deleteCategory(id)
        Result.success(Unit)
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun applyToContext(request: BehaviorCategoryMappingRequest) = try {
        api.applyToContext(request)
        Result.success(Unit)
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun removeFromContext(request: BehaviorCategoryMappingRequest) = try {
        api.removeFromContext(request)
        Result.success(Unit)
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun getCategoriesByTeacher() = try {
        Result.success(api.getCategoriesByTeacher().behaviorCategories)
    } catch (e: Exception) { Result.failure(e) }

    override suspend fun getCategoriesByContext(contextId: Int) = try {
        Result.success(api.getCategoriesByContext(contextId).behaviorCategories)
    } catch (e: Exception) { Result.failure(e) }
}