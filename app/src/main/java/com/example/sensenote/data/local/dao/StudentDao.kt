package com.example.sensenote.data.local.dao

import androidx.room.*
import com.example.sensenote.data.local.entities.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM students WHERE classroomId = :classId")
    fun getStudentsByClass(classId: String): Flow<List<StudentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<StudentEntity>)

    @Query("DELETE FROM students WHERE id = :studentId")
    suspend fun deleteStudent(studentId: String)
}