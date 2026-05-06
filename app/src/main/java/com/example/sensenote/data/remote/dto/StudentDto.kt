package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StudentDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("dob") val dob: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("isWarning") val isWarning: Boolean?,
    @SerializedName("classroomId") val classroomId: String,
    @SerializedName("thresholds") val thresholds: ThresholdsDto
)

data class SensoryThresholdsDto(
    @SerializedName("noise") val noise: Int,
    @SerializedName("light") val light: Int,
    @SerializedName("touch") val touch: Int
)