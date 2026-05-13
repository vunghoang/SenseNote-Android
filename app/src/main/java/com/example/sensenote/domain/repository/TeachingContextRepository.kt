package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.CreateTeachingContextRequest
import com.example.sensenote.data.remote.dto.TeachingContextDto

interface TeachingContextRepository {
    // Trả về danh sách lớp học dưới dạng Result để dễ xử lý lỗi ở ViewModel
    suspend fun getMyContexts(): Result<List<TeachingContextDto>>

    // Trả về ID của bối cảnh vừa tạo thành công
    suspend fun createNewContext(request: CreateTeachingContextRequest): Result<Int>
}