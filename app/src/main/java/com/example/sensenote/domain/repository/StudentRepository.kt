package com.example.sensenote.domain.repository

import com.example.sensenote.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getStudentsByClass(classId: String): Flow<List<Student>>
    suspend fun getStudentInfo(studentId: String): Result<Student>
    suspend fun addStudent(student: Student): Result<Unit>
    suspend fun deleteStudent(studentId: String): Result<Unit>
    suspend fun updateStudent(student: Student): Result<Unit>
}