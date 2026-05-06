package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.domain.model.*
import com.example.sensenote.domain.usecase.UpdateSeatAssignmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassMapViewModel @Inject constructor(
    private val updateSeatAssignmentUseCase: UpdateSeatAssignmentUseCase
) : ViewModel() {

    private val _mapState = MutableStateFlow<ClassMapUiState>(ClassMapUiState.Loading)
    val mapState = _mapState.asStateFlow()

    fun isPositionSafe(student: Student, x: Int, y: Int, context: TeachingContext): Boolean {
        val zone = context.sensoryZones.find { it.x == x && it.y == y }
        return if (zone != null) {
            val noiseThreshold = student.sensoryThresholds["Tiếng ồn"] ?: 0

            zone.intensity <= noiseThreshold
        } else true
    }

    fun moveStudent(studentId: String, newX: Int, newY: Int) {
        viewModelScope.launch {
        }
    }
}

sealed class ClassMapUiState {
    object Loading : ClassMapUiState()
    data class Success(
        val context: TeachingContext,
        val assignments: List<SeatAssignment>,
        val students: List<Student>
    ) : ClassMapUiState()
}