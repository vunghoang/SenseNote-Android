package com.example.sensenote.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensenote.presentation.ui.components.BottomNavigation
import com.example.sensenote.presentation.ui.components.StudentCard
import com.example.sensenote.presentation.ui.navigation.Screen
import com.example.sensenote.presentation.viewmodel.StudentListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    navController: NavController,
    contextId: Int,
    className: String,
    rows: Int,
    cols: Int,
    seatsPerTable: Int,
    viewModel: StudentListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(contextId) {
        viewModel.loadStudents(contextId)
    }

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddStudent.createRoute(contextId, className, rows, cols, seatsPerTable))
                },
                containerColor = Color(0xFF635BFF)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9FE))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Lớp $className", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(text = "Sơ đồ: ${rows}x${cols}", color = Color.Gray)
            }

            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                placeholder = { Text("Tìm tên...") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(50),
                leadingIcon = { Icon(Icons.Default.Search, null) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            if (state.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF635BFF))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // SỬA TẠI ĐÂY: Sử dụng displayName thay vì name
                    val filtered = state.students.filter { it.displayName.contains(state.searchQuery, true) }

                    // SỬA TẠI ĐÂY: Sử dụng studentId thay vì id làm key
                    items(filtered, key = { it.studentId }) { student ->
                        StudentCard(
                            student = student, // Đảm bảo StudentCard nhận SeatAssignmentDto hoặc cập nhật nó tương ứng
                            onClick = { id ->
                                navController.navigate(Screen.StudentDetail.createRoute(id.toString()))
                            },
                            onEdit = { /* TODO */ },
                            // SỬA TẠI ĐÂY: Sử dụng studentId
                            onDelete = { viewModel.deleteStudent(student.studentId, contextId) }
                        )
                    }
                }
            }
        }
    }
}