package com.example.sensenote.domain.usecase

import com.example.sensenote.domain.model.Student
import com.example.sensenote.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudentListUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    operator fun invoke(classId: String): Flow<List<Student>> {
        return repository.getStudentsByClass(classId)
    }
}