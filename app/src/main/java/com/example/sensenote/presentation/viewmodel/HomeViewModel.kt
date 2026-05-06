package com.example.sensenote.presentation.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensenote.domain.model.ClassItem
import com.example.sensenote.domain.repository.ClassRepository
import com.example.sensenote.presentation.ui.components.NumberPicker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val classes: List<ClassItem> = emptyList(),
    val teacherName: String = "Nguyễn A",
    val error: String? = null,
    val showAddClassDialog: Boolean = false
)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val classRepository: ClassRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

//    init {
//        loadDashboardData()
//    }
//
//    private fun loadDashboardData() {
//        viewModelScope.launch {
//            uiState = uiState.copy(isLoading = true)
//            val result = classRepository.getAllClasses()
//            if (result.isSuccess) {
//                uiState = uiState.copy(classes = result.getOrDefault(emptyList()), isLoading = false)
//            } else {
//                uiState = uiState.copy(error = "Không thể tải danh sách lớp", isLoading = false)
//            }
//        }
//    }
    init {
        loadMockData()
    }

    fun loadMockData() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            classRepository.getAllClasses().onSuccess { list ->
                uiState = uiState.copy(
                    classes = list,
                    teacherName = "Nguyễn A",
                    isLoading = false
                )
            }
        }
    }

    fun onShowAddClassDialog(show: Boolean) {
        uiState = uiState.copy(showAddClassDialog = show)
    }

    fun addClass(name: String, rows: Int, cols: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            delay(500)

            val newClass = ClassItem(
                id = (uiState.classes.size + 1).toString(),
                className = name,
                studentCount = 0,
                rows = rows,
                cols = cols,
                hasWarning = false
            )

            uiState = uiState.copy(
                classes = uiState.classes + newClass,
                isLoading = false,
                showAddClassDialog = false
            )
        }
    }
}

@Composable
fun AddClassDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Int, Int) -> Unit
) {
    var className by remember { mutableStateOf("") }
    var rows by remember { mutableIntStateOf(5) }
    var cols by remember { mutableIntStateOf(5) }

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(24.dp),
        containerColor = Color.White,
        title = {
            Text("Thêm lớp học mới", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                OutlinedTextField(
                    value = className,
                    onValueChange = { className = it },
                    label = { Text("Tên lớp (VD: 3A)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    NumberPicker(
                        label = "Số hàng",
                        value = rows,
                        onValueChange = { rows = it }
                    )
                    NumberPicker(
                        label = "Số cột",
                        value = cols,
                        onValueChange = { cols = it }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { if (className.isNotBlank()) onConfirm(className, rows, cols) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF635BFF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Thêm lớp")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy", color = Color.Gray)
            }
        }
    )
}



