package com.example.sensenote.presentation.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.sensenote.presentation.ui.components.SenseNoteBottomNavigation

@Composable
fun SeatMapScreen(navController: NavController) {
    val gridPadding = 20.dp
    val hSpacing = 16.dp
    val vSpacing = 24.dp

    val seats = listOf(
        Seat("Nguyễn Văn A", isWarning = true, Color(0xFFDCEDC8)), Seat("B", isWarning = true, Color(0xFFF48FB1)),
        Seat("C", avatarColor = Color(0xFF455A64)), Seat("D", avatarColor = Color(0xFFEF9A9A)),
        Seat("E", avatarColor = Color(0xFF80DEEA)), Seat("F", avatarColor = Color(0xFFFFF59D)),
        Seat("G", avatarColor = Color(0xFFB39DDB)), Seat("H", avatarColor = Color(0xFF90CAF9)),
        Seat("I", avatarColor = Color(0xFF9FA8DA)), Seat("K", avatarColor = Color(0xFFBCAAA4)),
        Seat("L", avatarColor = Color(0xFFFF1744)), Seat("M", avatarColor = Color(0xFF81C784)),
        Seat("N", avatarColor = Color(0xFF9575CD)), Seat("O", avatarColor = Color(0xFF66BB6A)),
        Seat("Q", avatarColor = Color(0xFF00695C)), Seat("R", avatarColor = Color(0xFF80DEEA))
    )

    Scaffold(
        bottomBar = { SenseNoteBottomNavigation(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF1F3F9))
        ) {
            SeatMapTopBar()

            BoxWithConstraints(modifier = Modifier.weight(1f).fillMaxWidth()) {
                val totalWidth = maxWidth
                val seatWidth = (totalWidth - (gridPadding * 2) - (hSpacing * 3)) / 4
                val seatHeight = seatWidth / 0.85f

                Box(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .offset(x = gridPadding - 8.dp, y = gridPadding - 8.dp)
                            .size(
                                width = (seatWidth * 2) + hSpacing + 16.dp,
                                height = (seatHeight * 2) + vSpacing + 16.dp
                            )
                            .clip(RoundedCornerShape(32.dp))
                            .background(Color(0xFFCE93D8).copy(alpha = 0.5f))
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = -(gridPadding - 8.dp), y = gridPadding - 8.dp)
                            .size(
                                width = seatWidth + 16.dp,
                                height = (seatHeight * 4) + (vSpacing * 3) + 16.dp
                            )
                            .clip(RoundedCornerShape(32.dp))
                            .background(Color(0xFF455A64).copy(alpha = 0.8f))
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(gridPadding),
                    horizontalArrangement = Arrangement.spacedBy(hSpacing),
                    verticalArrangement = Arrangement.spacedBy(vSpacing),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(seats) { seat ->
                        StudentSeatCard(seat)
                    }
                }
            }

            BottomLegendSection()
        }
    }
}

@Composable
fun StudentSeatCard(seat: Seat) {
    Card(
        modifier = Modifier.aspectRatio(0.85f),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(seat.avatarColor)
                    .then(if (seat.isWarning) Modifier.border(2.dp, Color.Red, CircleShape) else Modifier),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, null, tint = Color.Gray, modifier = Modifier.size(26.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = seat.studentName,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SeatMapTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Lớp 3A - Sơ đồ", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Row {
            Icon(Icons.Default.VisibilityOff, null, tint = Color(0xFF635BFF), modifier = Modifier.padding(horizontal = 8.dp).size(28.dp))
            Icon(Icons.Default.GridOn, null, tint = Color(0xFF635BFF), modifier = Modifier.padding(horizontal = 8.dp).size(28.dp))
            Icon(Icons.Default.Edit, null, tint = Color(0xFF635BFF), modifier = Modifier.padding(horizontal = 8.dp).size(28.dp))
        }
    }
}

@Composable
fun BottomLegendSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.weight(1f).height(120.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center) {
                LegendItem(Color(0xFFCE93D8), "Khu vực tiếng ồn lớn.")
                Spacer(modifier = Modifier.height(12.dp))
                LegendItem(Color(0xFF455A64), "Khu vực gây mất tập trung.")
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.size(120.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(Color(0xFF80DEEA)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, tint = Color.Gray)
                }
                Text("Giáo viên", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(18.dp).clip(CircleShape).background(color))
        Text("  $text", style = MaterialTheme.typography.bodySmall)
    }
}

data class Seat(val studentName: String, val isWarning: Boolean = false, val avatarColor: Color)