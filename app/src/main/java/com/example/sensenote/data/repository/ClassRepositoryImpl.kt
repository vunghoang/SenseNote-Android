package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.ApiService
import com.example.sensenote.domain.model.ClassItem
import com.example.sensenote.domain.repository.ClassRepository

import javax.inject.Inject

class ClassRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ClassRepository {

//    override suspend fun getAllClasses(): Result<List<ClassItem>> {
//        return try {
//            // Gọi endpoint Classes từ server .NET
//            val response = apiService.getAllClasses()
//            if (response.isSuccessful) {
//                val items = response.body()?.map {
//                    ClassItem(it.id, it.name, it.currentStudentCount, it.statusWarning)
//                } ?: emptyList()
//                Result.success(items)
//            } else Result.failure(Exception("Lỗi kết nối"))
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

    override suspend fun getAllClasses(): Result<List<ClassItem>> {
        val mockData = listOf(
            ClassItem("1", "Lớp 3A", 25, hasWarning = true, 4, 4),
            ClassItem("2", "Lớp 3B", 26, hasWarning = false, 4, 4),
            ClassItem("3", "Lớp 4A", 28, hasWarning = false, 4, 4),
            ClassItem("4", "Lớp 5C", 24, hasWarning = false, 4, 4)
        )
        return Result.success(mockData)
    }
}