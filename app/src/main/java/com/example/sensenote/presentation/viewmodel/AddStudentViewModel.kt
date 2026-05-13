package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.data.remote.dto.AddStudentRequest
import com.example.sensenote.domain.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddStudentUiState(
    val name: String = "",
    val dob: String = "",
    val selectedSeat: Int? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class AddStudentViewModel @Inject constructor(private val repository: StudentRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AddStudentUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) { _uiState.update { it.copy(name = name) } }
    fun onDobChange(dob: String) { _uiState.update { it.copy(dob = dob) } }
    fun onSeatSelected(index: Int) { _uiState.update { it.copy(selectedSeat = index) } }
    fun resetState() { _uiState.value = AddStudentUiState() }

    fun addStudent(classId: Int, teachingContextId: Int) {
        val state = _uiState.value

        // Validation cơ bản
        if (state.name.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Nhập tên") }
            return
        }
        if (state.selectedSeat == null) {
            _uiState.update { it.copy(errorMessage = "Chọn chỗ ngồi") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val request = AddStudentRequest(
                fullName = state.name,
                displayName = state.name,
                birthday = if (state.dob.isNotBlank()) state.dob else null, // Xử lý ngày sinh
                classId = classId,
                teachingContextId = teachingContextId,
                ordinalIndex = state.selectedSeat,
                medicalNotes = null
            )

            repository.addStudent(request).onSuccess {
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            }.onFailure { t ->
                _uiState.update { it.copy(isLoading = false, errorMessage = t.message) }
            }
        }
    }
}