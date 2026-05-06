package com.example.sensenote.domain.repository

import com.example.sensenote.domain.model.ClassItem

interface ClassRepository {
    suspend fun getAllClasses(): Result<List<ClassItem>>
}