package com.example.sensenote.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey val id: String,
    val name: String,
    val classroomId: String,
    val noiseTolerance: Int,
    val lightTolerance: Int,
    val touchTolerance: Int
)