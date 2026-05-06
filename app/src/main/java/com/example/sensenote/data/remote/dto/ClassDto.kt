package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ClassDto(
    @SerializedName("id")val id: String,
    @SerializedName("name")val name: String,
    @SerializedName("currentStudentCount")val currentStudentCount: Int,
    @SerializedName("statusWarning")val statusWarning: Boolean
)