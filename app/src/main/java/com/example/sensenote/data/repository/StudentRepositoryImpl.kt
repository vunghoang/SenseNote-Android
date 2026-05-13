package com.example.sensenote.data.repository

import com.example.sensenote.data.remote.StudentApi
import com.example.sensenote.data.remote.dto.*
import com.example.sensenote.domain.repository.StudentRepository
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val api: StudentApi
) : StudentRepository {

    override suspend fun addStudent(request: AddStudentRequest): Result<AddStudentResponse> {
        return try {
            Result.success(api.addStudent(request))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStudentInfo(contextId: Int, studentId: Int): Result<StudentInfoVm> {
        return try {
            val data = api.getStudentInfo(contextId, studentId)
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteStudent(id: Int): Result<Unit> {
        return try {
            api.deleteStudent(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateStudent(request: UpdateStudentRequest): Result<List<String>> {
        return try {
            val data = api.updateStudent(request)
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStudentsByClass(classId: Int): Result<List<StudentSummaryDto>> {
        return try {
            val response = api.getStudentsByClass(classId)
            Result.success(response.students)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}