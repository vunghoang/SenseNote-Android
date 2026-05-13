package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.*
import retrofit2.http.*

interface BehaviorCategoryApi {
    @POST("BehaviorCategories") // Route PascalCase khớp với Backend
    suspend fun createCategory(@Body request: CreateBehaviorCategoryRequest): Int

    @PUT("BehaviorCategories")
    suspend fun updateCategory(@Body request: UpdateBehaviorCategoryRequest)

    @DELETE("BehaviorCategories/{id}")
    suspend fun deleteCategory(@Path("id") id: Int)

    // Khớp với Route "apply"
    @POST("BehaviorCategories/apply")
    suspend fun applyToContext(@Body request: BehaviorCategoryMappingRequest)

    // Khớp với Route "remove"
    @HTTP(method = "DELETE", path = "BehaviorCategories/remove", hasBody = true)
    suspend fun removeFromContext(@Body request: BehaviorCategoryMappingRequest)

    @GET("BehaviorCategories")
    suspend fun getCategoriesByTeacher(): BehaviorCategoriesVm

    @GET("BehaviorCategories/by-teaching-context/{teachingContextId}")
    suspend fun getCategoriesByContext(@Path("teachingContextId") id: Int): BehaviorCategoriesVm
}