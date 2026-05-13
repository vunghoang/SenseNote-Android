package com.example.sensenote.domain.repository

import com.example.sensenote.data.remote.dto.*

interface StudentRepository {
    suspend fun addStudent(request: AddStudentRequest): Result<AddStudentResponse>

    suspend fun getStudentInfo(contextId: Int, studentId: Int): Result<StudentInfoVm>

    suspend fun deleteStudent(id: Int): Result<Unit>

    suspend fun updateStudent(request: UpdateStudentRequest): Result<List<String>>

    suspend fun getStudentsByClass(classId: Int): Result<List<StudentSummaryDto>>
}