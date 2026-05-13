package com.example.sensenote.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensenote.presentation.viewmodel.AddStudentViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(
    navController: NavController, contextId: Int, rows: Int, cols: Int,
    viewModel: AddStudentViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            scope.launch {
                snackbarHostState.showSnackbar("Đã thêm học sinh thành công!")
                viewModel.resetState()
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        viewModel.onDobChange(sdf.format(Date(it)))
                    }
                    showDatePicker = false
                }) { Text("Xác nhận") }
            },
            dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Hủy") } }
        ) { DatePicker(state = datePickerState) }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Thêm học sinh mới", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, null) }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize().background(Color(0xFFF8F9FE)).verticalScroll(rememberScrollState()).padding(24.dp)) {
            OutlinedTextField(value = state.name, onValueChange = viewModel::onNameChange, label = { Text("Họ và tên") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            Box(Modifier.fillMaxWidth().clickable { showDatePicker = true }) {
                OutlinedTextField(value = state.dob, onValueChange = {}, label = { Text("Ngày sinh") }, modifier = Modifier.fillMaxWidth(), enabled = false, trailingIcon = { Icon(Icons.Default.CalendarToday, null) }, colors = OutlinedTextFieldDefaults.colors(disabledTextColor = Color.Black, disabledContainerColor = Color.White))
            }
            Spacer(Modifier.height(24.dp))
            Text("Chọn vị trí ngồi", style = MaterialTheme.typography.titleSmall, color = Color.Gray)
            Card(Modifier.fillMaxWidth().padding(vertical = 8.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    for (r in 0 until rows) {
                        Row {
                            for (c in 0 until cols) {
                                val index = r * cols + c
                                val isSelected = state.selectedSeat == index
                                Surface(
                                    modifier = Modifier.padding(4.dp).size(40.dp).clickable { viewModel.onSeatSelected(index) },
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) Color(0xFF635BFF) else Color(0xFFE3F2FD)
                                ) { Box(contentAlignment = Alignment.Center) { Text((index + 1).toString(), color = if (isSelected) Color.White else Color(0xFF1976D2)) } }
                            }
                        }
                    }
                }
            }
            if (state.errorMessage != null) Text(state.errorMessage!!, color = Color.Red)
            Button(onClick = { viewModel.addStudent(contextId, contextId) }, modifier = Modifier.fillMaxWidth().padding(top = 24.dp).height(56.dp), shape = RoundedCornerShape(16.dp), enabled = !state.isLoading, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF635BFF))) {
                if (state.isLoading) CircularProgressIndicator(Modifier.size(24.dp), Color.White) else Text("Xác nhận thêm")
            }
        }
    }
}