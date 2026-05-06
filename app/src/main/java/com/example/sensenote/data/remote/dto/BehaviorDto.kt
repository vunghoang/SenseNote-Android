package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BehaviorLogRequest(
    @SerializedName("studentId") val studentId: String,
    @SerializedName("antecedent") val antecedent: String,
    @SerializedName("behavior") val behavior: String,
    @SerializedName("consequence") val consequence: String,
    @SerializedName("timestamp") val timestamp: String, // ISO 8601 string
    @SerializedName("location") val location: String?
)