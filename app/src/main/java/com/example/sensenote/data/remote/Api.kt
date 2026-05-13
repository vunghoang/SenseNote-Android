package com.example.sensenote.data.remote

import com.example.sensenote.data.remote.dto.ApiResponse

suspend inline fun <T> apiCall(
    crossinline call: suspend () -> ApiResponse<T>
): T {
    val response = call()

    if (!response.success) {
        throw Exception(response.message ?: "Đã có lỗi xảy ra từ máy chủ")
    }

    return response.data ?: throw Exception("Dữ liệu trả về trống")
}