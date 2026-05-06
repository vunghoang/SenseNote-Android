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
fun SenseNoteBottomNavigation(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
                IconButton(onClick = {
                    if (currentRoute != Screen.SeatMap.route) {
                        navController.navigate(Screen.SeatMap.route)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Dashboard,
                        contentDescription = "Sơ đồ lớp",
                        tint = if (currentRoute == Screen.SeatMap.route) activeColor else inactiveColor
                    )
                }

                IconButton(onClick = {
                    if (currentRoute != Screen.StudentList.route) {
                        navController.navigate(Screen.StudentList.route)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.People,
                        contentDescription = "Danh sách học sinh",
                        tint = if (currentRoute == Screen.StudentList.route) activeColor else inactiveColor
                    )
                }

                Spacer(modifier = Modifier.width(56.dp))

                IconButton(onClick = { /* navController.navigate(Screen.Diary.route) */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Description,
                        contentDescription = "Nhật ký",
                        tint = if (currentRoute == "diary_route") activeColor else inactiveColor
                    )
                }

                IconButton(onClick = {
                    if (currentRoute != Screen.Report.route) {
                        navController.navigate(Screen.Report.route)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.BarChart,
                        contentDescription = "Báo cáo",
                        tint = if (currentRoute == "report_route") activeColor else inactiveColor
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .offset(y = (-25).dp)
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