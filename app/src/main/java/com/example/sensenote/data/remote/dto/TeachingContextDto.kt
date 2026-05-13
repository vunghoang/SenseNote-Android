package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TeachingContextDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("contextName")
    val contextName: String,

    @SerializedName("numCols")
    val numCols: Int,

    @SerializedName("numRows")
    val numRows: Int,

    @SerializedName("seatsPerTable")
    val seatsPerTable: Int,

    @SerializedName("environmentalAssets")
    val environmentalAssets: List<EnvironmentalAssetDto> = emptyList()
)

data class TeachingContextsVm(
    @SerializedName("teachingContexts")
    val items: List<TeachingContextDto>? = emptyList()
)

data class CreateTeachingContextRequest(
    @SerializedName("className")
    val className: String,

    @SerializedName("teachingContextName")
    val teachingContextName: String,

    @SerializedName("numCols")
    val numCols: Int,

    @SerializedName("numRows")
    val numRows: Int,

    @SerializedName("seatsPerTable")
    val seatsPerTable: Int,

    @SerializedName("environmentalAssets")
    val environmentalAssets: List<EnvironmentalAssetDto> = emptyList()
)
data class EnvironmentalAssetDto(
    @SerializedName("assetType")
    val assetType: String,

    @SerializedName("x")
    val x: Int,

    @SerializedName("y")
    val y: Int,

    @SerializedName("influenceRadius")
    val influenceRadius: Double,

    @SerializedName("impactType")
    val impactType: Int
)