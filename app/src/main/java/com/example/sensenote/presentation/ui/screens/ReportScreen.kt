package com.example.sensenote.presentation.ui.screens
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.*
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.*
//import androidx.compose.ui.graphics.drawscope.Stroke
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.*
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.sensenote.presentation.ui.components.SenseNoteBottomNavigation
//import com.example.sensenote.presentation.ui.components.StudentSummaryCard
//import com.example.sensenote.presentation.ui.navigation.Screen
//import com.example.sensenote.presentation.viewmodel.StudentDetailViewModel
//
//@Composable
//fun ReportScreen(
//    navController: NavController,
//    studentId: String,
//    viewModel: StudentDetailViewModel = hiltViewModel()
//) {
//    LaunchedEffect(studentId) {
//        viewModel.loadStudent(studentId)
//    }
//
//    val student = viewModel.student
//
//    Scaffold(
//        bottomBar = { SenseNoteBottomNavigation(navController) }
//    ) { padding ->
//        if (student == null) {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                CircularProgressIndicator(color = Color(0xFF635BFF))
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .padding(padding)
//                    .fillMaxSize()
//                    .background(Color(0xFFF1F3F9))
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Text(
//                    text = "${student.name} - Biểu đồ",
//                    modifier = Modifier.padding(24.dp),
//                    style = MaterialTheme.typography.headlineSmall,
//                    fontWeight = FontWeight.Bold
//                )
//
//                StudentSummaryCard(student = student)
//
//                ChartSectionTitle("Tác nhân kích thích chính")
//                StimuliBarChart(student.sensoryThresholds)
//
//                ChartSectionTitle("Tần suất hành vi")
//                BehaviorLineChart()
//
//                ChartSectionTitle("Đề xuất")
//                Row(
//                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    SuggestionChip("Đổi chỗ")
//                    SuggestionChip("Đeo tai nghe", isPrimary = true)
//                }
//
//                Box(
//                    modifier = Modifier.fillMaxWidth().padding(24.dp),
//                    contentAlignment = Alignment.CenterEnd
//                ) {
//                    Button(
//                        onClick = {
//                            navController.navigate(Screen.StudentDetail.createRoute(studentId)) {
//                                popUpTo(Screen.Report.route) { inclusive = true }
//                            }
//                        },
//                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF635BFF)),
//                        shape = RoundedCornerShape(12.dp),
//                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
//                    ) {
//                        Text("Xem hành vi", fontWeight = FontWeight.Bold)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ChartSectionTitle(title: String) {
//    Text(
//        text = title,
//        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
//        style = MaterialTheme.typography.titleLarge,
//        fontWeight = FontWeight.Bold
//    )
//}
//
//@Composable
//fun StimuliBarChart(thresholds: Map<String, Int>) {
//    Card(
//        modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
//        shape = RoundedCornerShape(24.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(modifier = Modifier.padding(20.dp)) {
//            // Hiển thị dữ liệu từ Map sensoryThresholds
//            thresholds.forEach { (label, value) ->
//                StimuliRow(label, value / 100f, getBarColor(label))
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//fun getBarColor(label: String): Color {
//    return when {
//        label.contains("ồn") -> Color(0xFFCE93D8)
//        label.contains("sáng") -> Color(0xFF90CAF9)
//        else -> Color(0xFFA5D6A7)
//    }
//}
//
//@Composable
//fun StimuliRow(label: String, progress: Float, color: Color) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Text(label, modifier = Modifier.width(80.dp), style = MaterialTheme.typography.bodyMedium)
//        Box(
//            modifier = Modifier
//                .weight(1f)
//                .height(24.dp)
//                .clip(RoundedCornerShape(4.dp))
//                .background(Color(0xFFF1F3F9))
//                .border(1.0.dp, Color.Black, RoundedCornerShape(4.dp))
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth(progress.coerceIn(0f, 1f))
//                    .fillMaxHeight()
//                    .background(color)
//            )
//        }
//    }
//}
//
//@Composable
//fun BehaviorLineChart() {
//    val dataPoints = listOf(1.3f, 0.7f, 3.0f, 1.4f, 1.4f, 1.0f, 1.0f)
//    val xLabels = listOf("6:00", "8:00", "10:00", "12:00")
//    val yLabels = listOf("0", "1", "2", "3", "4")
//
//    Card(
//        modifier = Modifier
//            .padding(horizontal = 24.dp)
//            .fillMaxWidth()
//            .height(280.dp),
//        shape = RoundedCornerShape(24.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Canvas(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 30.dp, bottom = 50.dp, start = 45.dp, end = 25.dp)
//        ) {
//            val width = size.width
//            val height = size.height
//            val maxVal = 4f
//            val spacingX = width / (dataPoints.size - 1)
//            val spacingY = height / (yLabels.size - 1)
//
//            yLabels.forEachIndexed { index, label ->
//                val y = height - (index * spacingY)
//                drawLine(Color.LightGray.copy(alpha = 0.4f), Offset(0f, y), Offset(width, y), 1f)
//                drawContext.canvas.nativeCanvas.drawText(
//                    label, -35f, y + 10f,
//                    android.graphics.Paint().apply { color = android.graphics.Color.GRAY; textSize = 32f; textAlign = android.graphics.Paint.Align.RIGHT }
//                )
//            }
//
//            dataPoints.forEachIndexed { index, _ ->
//                val x = index * spacingX
//                drawLine(Color.LightGray.copy(alpha = 0.4f), Offset(x, 0f), Offset(x, height), 1f)
//                if (index % 2 == 0 && index / 2 < xLabels.size) {
//                    drawContext.canvas.nativeCanvas.drawText(
//                        xLabels[index / 2], x, height + 45f,
//                        android.graphics.Paint().apply { color = android.graphics.Color.GRAY; textSize = 30f; textAlign = android.graphics.Paint.Align.CENTER }
//                    )
//                }
//            }
//
//            val path = Path().apply {
//                dataPoints.forEachIndexed { i, v ->
//                    val x = i * spacingX
//                    val y = height - (v / maxVal * height)
//                    if (i == 0) moveTo(x, y) else lineTo(x, y)
//                }
//            }
//            drawPath(path, Color(0xFF635BFF), style = Stroke(width = 4f, cap = StrokeCap.Round, join = StrokeJoin.Round))
//
//            dataPoints.forEachIndexed { i, v ->
//                val x = i * spacingX
//                val y = height - (v / maxVal * height)
//                drawCircle(Color.White, 8f, Offset(x, y))
//                drawCircle(Color(0xFF635BFF), 8f, Offset(x, y), style = Stroke(3f))
//            }
//        }
//    }
//}
//
//@Composable
//fun SuggestionChip(text: String, isPrimary: Boolean = false) {
//    Surface(
//        shape = RoundedCornerShape(50),
//        color = if (isPrimary) Color(0xFFE8EAF6) else Color.Transparent,
//        border = BorderStroke(2.dp, Color(0xFF635BFF)),
//        modifier = Modifier.clickable { }
//    ) {
//        Text(
//            text = text,
//            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
//            color = Color.Black,
//            fontWeight = FontWeight.Medium
//        )
//    }
//}