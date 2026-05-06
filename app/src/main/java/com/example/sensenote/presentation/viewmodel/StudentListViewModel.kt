package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.domain.model.Student
import com.example.sensenote.domain.usecase.GetStudentListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val getStudentListUseCase: GetStudentListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<StudentListUiState>(StudentListUiState.Loading)
    val uiState: StateFlow<StudentListUiState> = _uiState.asStateFlow()

    fun loadStudents(classId: String) {
        viewModelScope.launch {
            getStudentListUseCase(classId)
                .catch { e ->
                    _uiState.value = StudentListUiState.Error(e.message ?: "Lỗi không xác định")
                }
                .collect { students ->
                    if (students.isEmpty()) {
                        _uiState.value = StudentListUiState.Empty
                    } else {
                        _uiState.value = StudentListUiState.Success(students)
                    }
                }
        }
    }
}

sealed class StudentListUiState {
    object Loading : StudentListUiState()
    data class Success(val students: List<Student>) : StudentListUiState()
    data class Error(val message: String) : StudentListUiState()
    object Empty : StudentListUiState()
}