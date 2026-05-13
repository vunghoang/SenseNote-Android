package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.data.remote.dto.SeatAssignmentDto
import com.example.sensenote.data.remote.dto.UpdateSeatAssignmentRequest
import com.example.sensenote.domain.repository.SeatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SeatMapUiState(
    val seats: List<SeatAssignmentDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class SeatMapViewModel @Inject constructor(
    private val seatRepository: SeatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SeatMapUiState())
    val uiState = _uiState.asStateFlow()

    fun loadSeatMap(contextId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            seatRepository.getSeatAssignments(contextId)
                .onSuccess { data ->
                    _uiState.update { it.copy(seats = data, isLoading = false) }
                }
                .onFailure { ex ->
                    _uiState.update { it.copy(error = ex.message, isLoading = false) }
                }
        }
    }

    fun updateSeatAssignment(contextId: Int, studentId: Int, newOrdinalIndex: Int) {
        viewModelScope.launch {
            val request = UpdateSeatAssignmentRequest(
                teachingContextId = contextId,
                studentId = studentId,
                ordinalIndex = newOrdinalIndex
            )

            seatRepository.updateSeatAssignment(request).onSuccess {
                loadSeatMap(contextId) // Tải lại sơ đồ sau khi cập nhật thành công
            }
        }
    }
}