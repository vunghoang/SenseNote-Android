package com.example.sensenote.presentation.ui.screens
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.*
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.*
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.sensenote.presentation.ui.components.SenseNoteBottomNavigation
//import com.example.sensenote.presentation.ui.components.StudentSummaryCard
//import com.example.sensenote.presentation.ui.navigation.Screen
//import com.example.sensenote.presentation.viewmodel.StudentDetailViewModel
//
//data class LogItem(val icon: ImageVector, val label: String)
//
//@Composable
//fun StudentDetailScreen(
//    navController: NavController,
//    studentId: String,
//    viewModel: StudentDetailViewModel = hiltViewModel()
//) {
//    LaunchedEffect(studentId) { viewModel.loadStudent(studentId) }
//    val student = viewModel.student
//
//    Scaffold(
//        bottomBar = { SenseNoteBottomNavigation(navController) }
//    ) { padding ->
//        student?.let { data ->
//            Column(
//                modifier = Modifier
//                    .padding(padding)
//                    .fillMaxSize()
//                    .background(Color(0xFFF1F3F9))
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Text(
//                    text = "${data.name} - Thông tin học sinh",
//                    modifier = Modifier.padding(24.dp),
//                    style = MaterialTheme.typography.headlineSmall,
//                    fontWeight = FontWeight.Bold
//                )
//
//                StudentSummaryCard(student = data)
//
//                Row(
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text("Ghi nhận", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(Icons.Default.Cancel, null, tint = Color(0xFF635BFF), modifier = Modifier.size(24.dp))
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Surface(shape = RoundedCornerShape(50), color = Color.White, border = BorderStroke(1.dp, Color.LightGray)) {
//                            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
//                                Text("dd/mm/yyyy", style = MaterialTheme.typography.bodyMedium)
//                                Spacer(modifier = Modifier.width(8.dp))
//                                Icon(Icons.Default.DateRange, null, modifier = Modifier.size(20.dp), tint = Color(0xFF635BFF))
//                            }
//                        }
//                    }
//                }
//
//                LogDateHeader("dd/mm/yyyy")
//                LogCategoryGroup(listOf(
//                    LogItem(Icons.Default.VolumeUp, "Âm thanh lớn"),
//                    LogItem(Icons.Default.HearingDisabled, "Bịt tai"),
//                    LogItem(Icons.Default.Headset, "Dùng tai nghe")
//                ))
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                LogDateHeader("dd/mm/yyyy")
//                LogCategoryGroup(listOf(
//                    LogItem(Icons.Default.LightMode, "Ánh sáng mạnh"),
//                    LogItem(Icons.Default.DirectionsRun, "Chạy khỏi chỗ"),
//                    LogItem(Icons.Default.GridView, "Đổi chỗ")
//                ))
//
//                Box(modifier = Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.CenterEnd) {
//                    Button(
//                        onClick = { navController.navigate(Screen.Report.createRoute(data.id)) },
//                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF635BFF)),
//                        shape = RoundedCornerShape(12.dp)
//                    ) {
//                        Text("Xem biểu đồ", fontWeight = FontWeight.Bold)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun LogCategoryGroup(items: List<LogItem>) {
//    Surface(
//        modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
//        shape = RoundedCornerShape(40.dp),
//        color = Color(0xFFE8EAF6),
//        border = BorderStroke(2.dp, Color(0xFF635BFF))
//    ) {
//        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceAround) {
//            items.forEach { item ->
//                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(80.dp)) {
//                    Box(modifier = Modifier.size(64.dp).clip(CircleShape).background(Color(0xFF80DEEA)).border(2.dp, Color.Black, CircleShape), contentAlignment = Alignment.Center) {
//                        Icon(item.icon, null, modifier = Modifier.size(32.dp), tint = Color.Black)
//                    }
//                    Text(item.label, style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center, lineHeight = 12.sp)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun LogDateHeader(date: String) {
//    Text(date, modifier = Modifier.padding(start = 24.dp, bottom = 8.dp), color = Color.Gray, style = MaterialTheme.typography.bodySmall)
//}