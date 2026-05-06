package com.example.sensenote.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sensenote.domain.model.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentDetailViewModel @Inject constructor() : ViewModel() {

    var student by mutableStateOf<Student?>(null)
        private set

    private val mockStudents = listOf(
        Student("1", "Nguyễn Văn A", "10/05/2015", "Mất tập trung", true, "3A", mapOf("Tiếng ồn" to 80, "Ánh sáng" to 60)),
        Student("2", "Học sinh B", "12/08/2015", "Bình thường", false, "3A", mapOf("Tiếng ồn" to 40, "Ánh sáng" to 30)),
        Student("3", "Học sinh C", "05/02/2015", "Hay làm ồn", true, "3A", mapOf("Tiếng ồn" to 90, "Ánh sáng" to 50))
    )

    fun loadStudent(studentId: String) {
        student = mockStudents.find { it.id == studentId }
    }
}