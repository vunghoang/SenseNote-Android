package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BehaviorCategoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("pointValue") val pointValue: Int,
    @SerializedName("icon") val icon: String? = null
)

data class BehaviorCategoriesVm(
    @SerializedName("behaviorCategories") val behaviorCategories: List<BehaviorCategoryDto>
)

data class CreateBehaviorCategoryRequest(
    @SerializedName("name") val name: String,
    @SerializedName("pointValue") val pointValue: Int,
    @SerializedName("icon") val icon: String? = null
)

data class UpdateBehaviorCategoryRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("pointValue") val pointValue: Int,
    @SerializedName("icon") val icon: String? = null
)

data class BehaviorCategoryMappingRequest(
    @SerializedName("teachingContextId") val teachingContextId: Int,
    @SerializedName("behaviorCategoryIds") val behaviorCategoryIds: List<Int>
)