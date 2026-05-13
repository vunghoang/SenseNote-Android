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

            // Gọi repository để lấy danh sách chỗ ngồi
            seatRepository.getSeatAssignments(contextId)
                .onSuccess { data ->
                    // Backend đã sắp xếp theo ordinalIndex
                    // Dữ liệu này chứa displayName và sensitiveLocations của từng học sinh
                    _uiState.update { it.copy(seats = data, isLoading = false) }
                }
                .onFailure { ex ->
                    _uiState.update { it.copy(error = ex.message, isLoading = false) }
                }
        }
    }

    /**
     * Cập nhật vị trí ghế (Ví dụ khi giáo viên kéo thả học sinh sang ghế khác)
     * Khớp với endpoint PUT trong Seats.cs
     */
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