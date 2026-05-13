package com.example.sensenote.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.data.remote.dto.BehaviorCategoryDto
import com.example.sensenote.data.remote.dto.CreateBehaviorCategoryRequest
import com.example.sensenote.domain.repository.BehaviorCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BehaviorViewModel @Inject constructor(
    private val repository: BehaviorCategoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BehaviorUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getCategoriesByTeacher().onSuccess { response ->
                _uiState.update { it.copy(items = response, isLoading = false) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }

    fun addCategory(name: String, point: Int, icon: String? = null) {
        viewModelScope.launch {
            val request = CreateBehaviorCategoryRequest(name, point, icon)
            repository.createCategory(request).onSuccess {
                loadCategories() // Refresh lại danh sách sau khi thêm
            }
        }
    }

    fun deleteCategory(id: Int) {
        viewModelScope.launch {
            repository.deleteCategory(id).onSuccess {
                loadCategories() // Refresh lại danh sách sau khi xóa
            }
        }
    }
}

data class BehaviorUiState(
    val items: List<BehaviorCategoryDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)