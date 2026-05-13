package com.example.sensenote.presentation.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensenote.presentation.ui.components.BottomNavigation
import com.example.sensenote.presentation.viewmodel.SeatMapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatMapScreen(
    navController: NavController,
    contextId: Int,
    className: String,
    rows: Int,
    cols: Int,
    seatsPerTable: Int,
    viewModel: SeatMapViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(contextId) {
        viewModel.loadSeatMap(contextId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lớp $className - Sơ đồ", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Outlined.AutoFixHigh, null, tint = Color(0xFF9FA8DA)) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.Visibility, null, tint = Color(0xFF9FA8DA)) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.Edit, null, tint = Color(0xFF9FA8DA)) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF8F9FE))
            )
        },
        bottomBar = { BottomNavigation(navController = navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9FE))
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (r in 0 until rows) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        for (c in 0 until cols) {
                            // SỬA LỖI: Tính toán index của bàn hiện tại (tableIndex)
                            // Một bàn tại (r, c) sẽ chứa các ghế có ordinalIndex nằm trong khoảng từ
                            // (r * cols + c) * seatsPerTable đến ((r * cols + c) + 1) * seatsPerTable - 1
                            val tableIndex = r * cols + c
                            val assignedInTable = state.seats.filter {
                                (it.ordinalIndex / seatsPerTable) == tableIndex
                            }

                            TableCard(
                                modifier = Modifier.weight(1f),
                                seatsPerTable = seatsPerTable,
                                assignedSeats = assignedInTable
                            )
                        }
                    }
                }
            }

            // ... phần Bục giảng & Chú thích giữ nguyên
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(
                    "BẢNG ĐEN / BỤC GIẢNG",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp, vertical = 4.dp)
                        .height(6.dp),
                    color = Color(0xFFCFD8DC),
                    shape = RoundedCornerShape(4.dp)
                ) {}
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    LegendItem(Color(0xFFCE93D8), "Khu vực tiếng ồn lớn.")
                    LegendItem(Color(0xFF006064), "Khu vực gây mất tập trung.")
                }
                TeacherStation()
            }
        }
    }
}

@Composable
fun TableCard(
    modifier: Modifier = Modifier,
    seatsPerTable: Int,
    assignedSeats: List<com.example.sensenote.data.remote.dto.SeatAssignmentDto>
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier.padding(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(seatsPerTable) { seatIndex ->
                // SỬA LỖI: Tìm học sinh có ordinalIndex khớp với vị trí ghế trong bàn này
                val student = assignedSeats.find { (it.ordinalIndex % seatsPerTable) == seatIndex }

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    StudentAvatarItem(
                        // Sử dụng displayName từ Backend
                        name = student?.displayName ?: "Trống",
                        isOccupied = student != null
                    )
                }
            }
        }
    }
}
@Composable
fun StudentAvatarItem(name: String, isOccupied: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(32.dp) // Kích thước avatar nhỏ gọn để vừa khít màn hình dọc
                .clip(CircleShape)
                .background(if (isOccupied) Color.White else Color(0xFFF5F5F5))
                .border(1.dp, if (isOccupied) Color(0xFF90CAF9) else Color.LightGray, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Person,
                null,
                tint = if (isOccupied) Color(0xFF90CAF9) else Color.LightGray,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = if (isOccupied) name.split(" ").last() else "", // Chỉ hiện tên riêng cho gọn
            style = MaterialTheme.typography.labelSmall,
            fontSize = 8.sp,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TeacherStation() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFB2EBF2)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, null, tint = Color(0xFF00838F), modifier = Modifier.size(28.dp))
        }
        Text("Giáo viên", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun LegendItem(color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(color))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.labelSmall, color = Color.Gray, fontSize = 10.sp)
    }
}