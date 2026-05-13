package com.example.sensenote.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sensenote.presentation.ui.navigation.Screen

@Composable
fun BottomNavigation(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val contextId = navBackStackEntry?.arguments?.getInt("contextId") ?: 0
    val className = navBackStackEntry?.arguments?.getString("className") ?: ""
    val rows = navBackStackEntry?.arguments?.getInt("rows") ?: 0
    val cols = navBackStackEntry?.arguments?.getInt("cols") ?: 0
    val seatsPerTable = navBackStackEntry?.arguments?.getInt("seatsPerTable") ?: 0

    val activeColor = Color(0xFF635BFF)
    val inactiveColor = Color(0xFF9FA8DA)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            color = Color(0xFFEFEFFF),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. Nút SƠ ĐỒ LỚP
                IconButton(onClick = {
                    if (currentRoute?.startsWith("seat_map") == false && contextId != 0) {
                        navController.navigate(
                            Screen.SeatMap.createRoute(contextId, className, rows, cols, seatsPerTable)
                        ) {
                            launchSingleTop = true
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Dashboard,
                        contentDescription = "Sơ đồ lớp",
                        tint = if (currentRoute?.startsWith("seat_map") == true) activeColor else inactiveColor
                    )
                }

                // 2. Nút DANH SÁCH HỌC SINH
                IconButton(onClick = {
                    if (currentRoute?.startsWith("student_list") == false && contextId != 0) {
                        navController.navigate(
                            Screen.StudentList.createRoute(contextId, className, rows, cols, seatsPerTable)
                        ) {
                            launchSingleTop = true
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.People,
                        contentDescription = "Danh sách học sinh",
                        tint = if (currentRoute?.startsWith("student_list") == true) activeColor else inactiveColor
                    )
                }

                Spacer(modifier = Modifier.width(56.dp))

                // 3. Nút Nhật ký
                IconButton(onClick = { /* Xử lý Diary sau */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Description,
                        contentDescription = "Nhật ký",
                        tint = if (currentRoute == Screen.Diary.route) activeColor else inactiveColor
                    )
                }

                // 4. Nút Báo cáo
                IconButton(onClick = {
                    if (currentRoute?.startsWith("report") == false) {
                        navController.navigate(Screen.Report.createRoute("0"))
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.BarChart,
                        contentDescription = "Báo cáo",
                        tint = if (currentRoute?.startsWith("report") == true) activeColor else inactiveColor
                    )
                }
            }
        }

        // Nút Home trung tâm
        Box(
            modifier = Modifier
                .offset(y = (-25.dp))
                .size(56.dp)
                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
                .background(activeColor)
                .clickable {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Trang chủ",
                tint = Color.White
            )
        }
    }
}