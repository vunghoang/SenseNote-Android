package com.example.sensenote.presentation.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensenote.presentation.viewmodel.AddClassDialog
import com.example.sensenote.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    onClassClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    if (state.showAddClassDialog) {
        AddClassDialog(
            onDismiss = { viewModel.onShowAddClassDialog(false) },
            onConfirm = { name, r, c ->
                viewModel.addClass(name, r, c)
            }
        )
    }

    Scaffold(
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9FE))
        ) {
            item {
                HomeTopBar(state.teacherName)
            }

            item {
                CalendarStrip()
            }

            item {
                Text(
                    "Danh sách lớp",
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                SearchBarSection(onAddClick = { viewModel.onShowAddClassDialog(true) })
            }

            items(state.classes.chunked(2)) { rowClasses ->
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    for (item in rowClasses) {
                        ClassCard(
                            item = item,
                            modifier = Modifier.weight(1f),
                            onClick = { onClassClick(item.id) }
                        )
                    }
                    if (rowClasses.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun HomeTopBar(name: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(50.dp).clip(CircleShape).background(Color(0xFFB2EBF2))) {
                Icon(Icons.Default.Person, null, modifier = Modifier.align(Alignment.Center))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("Xin chào!", style = MaterialTheme.typography.bodySmall)
                Text(name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            }
        }
        Icon(Icons.Default.Notifications, null, modifier = Modifier.size(28.dp))
    }
}

@Composable
fun ClassCard(item: com.example.sensenote.domain.model.ClassItem, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.padding(8.dp).clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(item.className, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            Text("Sĩ số: ${item.studentCount}", color = Color.Gray)
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(if(item.hasWarning) Color.Red else Color.Green))
                if(item.hasWarning) {
                    Text(" 1 cảnh báo mới", style = MaterialTheme.typography.bodySmall, color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun CalendarStrip() {
    val days = listOf(
        "MO" to "20", "TU" to "21", "WE" to "22",
        "TH" to "23", "FR" to "24", "SA" to "25", "SU" to "26"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        LazyRow(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false
        ) {
            items(days) { day ->
                val isSelected = day.second == "22"
                DateItem(day.first, day.second, isSelected)
            }
        }
    }
}

@Composable
fun DateItem(dayOfWeek: String, dayOfMonth: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .width(45.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color(0xFF1E88E5) else Color.Transparent)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = dayOfWeek,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) Color.White else Color.Gray,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = dayOfMonth,
            style = MaterialTheme.typography.titleMedium,
            color = if (isSelected) Color.White else Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SearchBarSection(
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { },
            placeholder = {
                Text("Search", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
            },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(50),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFF635BFF)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onAddClick() }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF635BFF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Thêm lớp",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Thêm lớp",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}