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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensenote.domain.model.Student
import com.example.sensenote.presentation.ui.components.SenseNoteBottomNavigation
import com.example.sensenote.presentation.ui.components.StudentCard
import com.example.sensenote.presentation.ui.navigation.Screen

@Composable
fun StudentListScreen(navController: NavController) {
    val mockStudents = listOf(
        Student(
            id = "1",
            name = "Nguyễn Văn A",
            dob = "10/05/2015",
            status = "Mất tập trung",
            isWarning = true,
            classroomId = "3A",
            sensoryThresholds = mapOf("Tiếng ồn" to 80, "Ánh sáng" to 60)
        ),
        Student(
            id = "2",
            name = "Học sinh B",
            dob = "12/08/2015",
            status = "Nhạy cảm tiếng ồn",
            isWarning = true,
            classroomId = "3A",
            sensoryThresholds = mapOf("Tiếng ồn" to 90, "Ánh sáng" to 40)
        ),
        Student(
            id = "3",
            name = "Học sinh C",
            dob = "05/02/2015",
            status = "Bình thường",
            isWarning = false,
            classroomId = "3A",
            sensoryThresholds = mapOf("Tiếng ồn" to 50, "Ánh sáng" to 50)
        )
    )

    Scaffold(
        bottomBar = { SenseNoteBottomNavigation(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9FE))
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text("Lớp 3A - Danh sách lớp", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("${mockStudents.size}/25", color = Color.Gray)
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "", onValueChange = {},
                    placeholder = { Text("Tìm kiếm học sinh...") },
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(50),
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = { },
                        modifier = Modifier.size(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF635BFF)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(Icons.Default.Add, null, modifier = Modifier.size(32.dp))
                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(mockStudents) { student ->
                    StudentCard(
                        student = student,
                        onClick = { studentId ->
                            navController.navigate(Screen.StudentDetail.createRoute(studentId))
                        },
                        onEdit = { },
                        onDelete = { }
                    )
                }
            }
        }
    }
}