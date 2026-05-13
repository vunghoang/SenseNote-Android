package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.data.remote.dto.SeatAssignmentDto
import com.example.sensenote.domain.repository.SeatRepository
import com.example.sensenote.domain.repository.StudentRepository // Vẫn cần để xóa học sinh
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StudentListUiState(
    // Chuyển sang sử dụng SeatAssignmentDto
    val students: List<SeatAssignmentDto> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val errorMessage: String? = null
)

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val seatRepository: SeatRepository,
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentListUiState())
    val uiState = _uiState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun loadStudents(contextId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // Lấy danh sách chỗ ngồi từ SeatRepository
            seatRepository.getSeatAssignments(contextId)
                .onSuccess { data ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        students = data // Data ở đây đã là List<SeatAssignmentDto>
                    )}
                }
                .onFailure { ex ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = ex.message) }
                }
        }
    }

    fun deleteStudent(studentId: Int, contextId: Int) {
        viewModelScope.launch {
            // Xóa học sinh dựa trên ID thực tế
            studentRepository.deleteStudent(studentId).onSuccess {
                loadStudents(contextId)
            }
        }
    }
}