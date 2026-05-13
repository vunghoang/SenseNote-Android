package com.example.sensenote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.data.remote.dto.CreateTeachingContextRequest
import com.example.sensenote.data.remote.dto.TeachingContextDto
import com.example.sensenote.domain.repository.TeachingContextRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val teacherName: String = "Giáo viên",
    val classes: List<TeachingContextDto> = emptyList(),
    val isLoading: Boolean = false,
    val showAddClassDialog: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TeachingContextRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadTeachingContexts()
    }

    fun loadTeachingContexts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getMyContexts().onSuccess { list ->
                _uiState.update { it.copy(classes = list, isLoading = false) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onShowAddClassDialog(show: Boolean) {
        _uiState.update { it.copy(showAddClassDialog = show) }
    }

    fun addClass(className: String, contextName: String, rows: Int, cols: Int, seats: Int) {
        viewModelScope.launch {
            val request = CreateTeachingContextRequest(
                className = className,
                teachingContextName = contextName,
                numRows = rows,
                numCols = cols,
                seatsPerTable = seats
            )
            repository.createNewContext(request).onSuccess {
                onShowAddClassDialog(false) // Đóng Dialog
                loadTeachingContexts() // QUAN TRỌNG: Gọi lại hàm này để cập nhật HomeScreen
            }
        }
    }
}