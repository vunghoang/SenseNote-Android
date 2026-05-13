package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

// Dữ liệu trả về từ GET
data class BehaviorCategoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("pointValue") val pointValue: Int, // Khớp với Backend
    @SerializedName("icon") val icon: String? = null
)

data class BehaviorCategoriesVm(
    @SerializedName("behaviorCategories") val behaviorCategories: List<BehaviorCategoryDto> // Khớp với Vm
)

// Dữ liệu gửi lên cho POST (Create)
data class CreateBehaviorCategoryRequest(
    @SerializedName("name") val name: String,
    @SerializedName("pointValue") val pointValue: Int,
    @SerializedName("icon") val icon: String? = null
)

// THÊM: Dữ liệu gửi lên cho PUT (Update) - Khớp với UpdateBehaviorCategoryCommand
data class UpdateBehaviorCategoryRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("pointValue") val pointValue: Int,
    @SerializedName("icon") val icon: String? = null
)

// THÊM: Dữ liệu cho Mapping (Apply/Remove) - Khớp với ApplyBehaviorCategoryToTeachingContextCommand
data class BehaviorCategoryMappingRequest(
    @SerializedName("teachingContextId") val teachingContextId: Int,
    @SerializedName("behaviorCategoryIds") val behaviorCategoryIds: List<Int>
)