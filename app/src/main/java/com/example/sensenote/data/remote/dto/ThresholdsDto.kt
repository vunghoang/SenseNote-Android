package com.example.sensenote.data.remote.dto

import com.google.gson.annotations.SerializedName
data class ThresholdsDto(
    @SerializedName("noise")
    val noise: Int, // Ngưỡng tiếng ồn

    @SerializedName("light")
    val light: Int, // Ngưỡng ánh sáng

    @SerializedName("touch")
    val touch: Int  // Ngưỡng xúc giác/chạm
)