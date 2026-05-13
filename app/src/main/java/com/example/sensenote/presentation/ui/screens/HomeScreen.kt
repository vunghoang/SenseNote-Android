package com.example.sensenote.presentation.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensenote.data.remote.dto.TeachingContextDto
import com.example.sensenote.presentation.ui.components.AddClassDialog
import com.example.sensenote.presentation.ui.navigation.Screen
import com.example.sensenote.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    onClassClick: (TeachingContextDto) -> Unit, // Đổi từ Int sang Dto để lấy đủ rows/cols
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    if (state.showAddClassDialog) {
        AddClassDialog(
            onDismiss = { viewModel.onShowAddClassDialog(false) },
            onConfirm = { className, contextName, rows, cols, seats ->
                viewModel.addClass(className, contextName, rows, cols, seats)
            }
        )
    }

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9FE))
        ) {
            item {
                // Cập nhật ở đây: Truyền lệnh điều hướng tới route behavior_setting
                HomeTopBar(
                    name = state.teacherName,
                    onSettingsClick = {
                        navController.navigate(Screen.BehaviorSetting.route)
                    }
                )
            }
            item { CalendarStrip() }
            item {
                Text(
                    "Danh sách lớp",
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            item { SearchBarSection(onAddClick = { viewModel.onShowAddClassDialog(true) }) }

            items(state.classes.chunked(2)) { rowClasses ->
                Row(modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()) {
                    for (item in rowClasses) {
                        ClassCard(
                            item = item,
                            modifier = Modifier.weight(1f),
                            onClick = { onClassClick(item) } // Truyền cả item
                        )
                    }
                    if (rowClasses.size == 1) Spacer(modifier = Modifier.weight(1f).padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun ClassCard(item: TeachingContextDto, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.padding(8.dp).clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(item.contextName, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            Text("Bố cục: ${item.numRows}x${item.numCols}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color.Green))
                Text(" Hoạt động", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}

// Giữ nguyên các component HomeTopBar, CalendarStrip, DateItem, SearchBarSection...

@Composable
fun HomeTopBar(name: String, onSettingsClick: () -> Unit) {

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

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Notifications,
                null,
                modifier = Modifier.size(28.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(12.dp))
            // Nút cài đặt mới
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Cài đặt hành vi",
                    modifier = Modifier.size(28.dp),
                    tint = Color(0xFF635BFF) // Màu tím chủ đạo của SenseNote
                )
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
