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
import com.example.sensenote.domain.model.Student
@Composable
fun StudentCard(
    student: Student,
    onClick: (String) -> Unit,
    onEdit: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick(student.id) },
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
                        color = if (student.isWarning) Color(0xFFFF1744) else Color(0xFF80DEEA),
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
                        .background(if (student.isWarning) Color(0xFFE1F5FE).copy(alpha = 0.5f) else Color(0xFFDCEDC8)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(30.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(student.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text(student.dob, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }
            }

            Box(modifier = Modifier.fillMaxHeight().padding(vertical = 12.dp).width(1.5.dp).background(Color.Black))

            Column(modifier = Modifier.weight(1f).padding(12.dp)) {
                Text("Tình trạng:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                    Box(
                        modifier = Modifier.size(8.dp).clip(CircleShape)
                            .background(if (student.isWarning) Color.Red else Color.Green)
                    )
                    Text("  ${student.status}", style = MaterialTheme.typography.bodySmall, color = Color.Black)
                }
            }

            Column(
                modifier = Modifier.padding(end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { onEdit(student.id) }, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Edit, null, tint = Color(0xFF7986CB), modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                IconButton(onClick = { onDelete(student.id) }, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Delete, null, tint = Color(0xFFFF5252), modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun StudentSummaryCard(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
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
                        color = if (student.isWarning) Color.Red else Color(0xFF80DEEA),
                        shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(if (student.isWarning) Color(0xFFFFEBEE) else Color(0xFFDCEDC8)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 16.dp)
                    .width(1.dp)
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Họ và tên: ${student.name}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text("Ngày sinh: ${student.dob}", style = MaterialTheme.typography.bodyMedium)
                Text("Tình trạng:", style = MaterialTheme.typography.bodyMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (student.isWarning) Color.Red else Color.Green)
                    )
                    Text(
                        "  ${student.status}", // ✅ Hiển thị đúng tình trạng của học sinh
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 12.dp, top = 12.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = Color(0xFF635BFF),
                    modifier = Modifier.size(20.dp).clickable { /* Xử lý sửa */ }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(20.dp).clickable { /* Xử lý xóa */ }
                )
            }
        }
    }
}