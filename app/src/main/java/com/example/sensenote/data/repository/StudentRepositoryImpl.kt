package com.example.sensenote.data.repository

import com.example.sensenote.data.mapper.toDomain
import com.example.sensenote.data.remote.ApiService
import com.example.sensenote.domain.model.Student
import com.example.sensenote.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : StudentRepository {

    override fun getStudentsByClass(classId: String): Flow<List<Student>> = flow {
        val dtos = apiService.getStudentsByClass(classId)
        emit(dtos.map { it.toDomain() })
    }

    override suspend fun getStudentInfo(studentId: String): Result<Student> {
        return try {
            val dto = apiService.getStudentInfo(studentId)
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addStudent(student: Student): Result<Unit> = Result.success(Unit)

    override suspend fun deleteStudent(studentId: String): Result<Unit> = Result.success(Unit)

    override suspend fun updateStudent(student: Student): Result<Unit> = Result.success(Unit)
}