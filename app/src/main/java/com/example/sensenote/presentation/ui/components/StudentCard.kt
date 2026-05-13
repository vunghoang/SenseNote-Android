package com.example.sensenote.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
// Sử dụng SeatAssignmentDto thay cho StudentInfoDto
import com.example.sensenote.data.remote.dto.SeatAssignmentDto

@Composable
fun StudentCard(
    student: SeatAssignmentDto,
    onClick: (Int) -> Unit,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    // Logic hiển thị dựa trên SensitiveLocations từ Backend
    val isWarning = student.sensitiveLocations.isNotEmpty()
    val statusText = if (isWarning) "Cần chú ý" else "Bình thường"
    val statusColor = if (isWarning) Color(0xFFFF1744) else Color(0xFF80DEEA)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick(student.studentId) }, // Sử dụng studentId
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(12.dp)
                    .background(
                        color = statusColor,
                        shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
                    )
            )

            Row(
                modifier = Modifier.weight(1.2f).padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(if (isWarning) Color(0xFFFFEBEE) else Color(0xFFDCEDC8)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, tint = Color.Gray, modifier = Modifier.size(30.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    // Sử dụng displayName
                    Text(student.displayName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    // OrdinalIndex thể hiện vị trí trong sơ đồ
                    Text("Vị trí: ${student.ordinalIndex}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }
            }

            Box(modifier = Modifier.fillMaxHeight().padding(vertical = 12.dp).width(1.5.dp).background(Color.Black.copy(alpha = 0.1f)))

            Column(modifier = Modifier.weight(1f).padding(12.dp)) {
                Text("Tình trạng:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                    Box(
                        modifier = Modifier.size(8.dp).clip(CircleShape)
                            .background(if (isWarning) Color.Red else Color.Green)
                    )
                    Text("  $statusText", style = MaterialTheme.typography.bodySmall, color = Color.Black)
                }
            }

            Column(
                modifier = Modifier.padding(end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { onEdit(student.studentId) }, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Edit, null, tint = Color(0xFF7986CB), modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                IconButton(onClick = { onDelete(student.studentId) }, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Delete, null, tint = Color(0xFFFF5252), modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}