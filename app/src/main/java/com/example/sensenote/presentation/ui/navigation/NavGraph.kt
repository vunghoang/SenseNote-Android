package com.example.sensenote.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sensenote.presentation.setting.BehaviorSettingScreen
import com.example.sensenote.presentation.setting.BehaviorViewModel
import com.example.sensenote.presentation.ui.screens.AddStudentScreen
import com.example.sensenote.presentation.ui.screens.HomeScreen
//import com.example.sensenote.presentation.ui.screens.StudentListScreen
import com.example.sensenote.presentation.ui.screens.LoginScreen // Đảm bảo bạn đã import LoginScreen
import com.example.sensenote.presentation.ui.screens.RegisterScreen
import com.example.sensenote.presentation.ui.screens.SeatMapScreen
import com.example.sensenote.presentation.ui.screens.StudentListScreen

//import com.example.sensenote.presentation.ui.screens.ReportScreen
//import com.example.sensenote.presentation.ui.screens.SeatMapScreen
//import com.example.sensenote.presentation.ui.screens.StudentDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                navController = navController,
                onRegisterSuccess = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                onClassClick = { context ->
                    navController.navigate(
                        Screen.SeatMap.createRoute(
                            context.id,
                            context.contextName, // Lấy tên thực tế từ Backend
                            context.numRows,
                            context.numCols,
                            context.seatsPerTable
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.SeatMap.route,
            arguments = listOf(
                navArgument("contextId") { type = NavType.IntType },
                navArgument("className") { type = NavType.StringType }, // Thêm tham số này
                navArgument("rows") { type = NavType.IntType },
                navArgument("cols") { type = NavType.IntType },
                navArgument("seatsPerTable") { type = NavType.IntType }
            )
        ) { entry ->
            val contextId = entry.arguments?.getInt("contextId") ?: 0
            val className = entry.arguments?.getString("className") ?: ""
            val rows = entry.arguments?.getInt("rows") ?: 0
            val cols = entry.arguments?.getInt("cols") ?: 0

            SeatMapScreen(
                navController = navController,
                contextId = entry.arguments?.getInt("contextId") ?: 0,
                className = entry.arguments?.getString("className") ?: "Lớp học", // Nhận tên lớp
                rows = entry.arguments?.getInt("rows") ?: 0,
                cols = entry.arguments?.getInt("cols") ?: 0,
                seatsPerTable = entry.arguments?.getInt("seatsPerTable") ?: 0
            )
        }

        composable(
            route = Screen.StudentList.route,
            arguments = listOf(
                navArgument("contextId") { type = NavType.IntType },
                navArgument("className") { type = NavType.StringType },
                navArgument("rows") { type = NavType.IntType },
                navArgument("cols") { type = NavType.IntType },
                navArgument("seatsPerTable") { type = NavType.IntType }
            )
        ) { entry ->
            StudentListScreen(
                navController = navController,
                contextId = entry.arguments?.getInt("contextId") ?: 0,
                className = entry.arguments?.getString("className") ?: "Lớp học",
                rows = entry.arguments?.getInt("rows") ?: 0,
                cols = entry.arguments?.getInt("cols") ?: 0,
                seatsPerTable = entry.arguments?.getInt("seatsPerTable") ?: 0
            )
        }

        composable(
            route = Screen.AddStudent.route, // Không dùng cộng chuỗi nữa
            arguments = listOf(
                navArgument("contextId") { type = NavType.IntType },
                navArgument("rows") { type = NavType.IntType },
                navArgument("cols") { type = NavType.IntType }
            )
        ) { entry ->
            AddStudentScreen(
                navController = navController,
                contextId = entry.arguments?.getInt("contextId") ?: 0,
                rows = entry.arguments?.getInt("rows") ?: 0,
                cols = entry.arguments?.getInt("cols") ?: 0
            )
        }

        composable(Screen.BehaviorSetting.route) {
            BehaviorSettingScreen(
                navController = navController,

            )
        }
//
//        composable(
//            route = Screen.StudentDetail.route,
//            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
//            StudentDetailScreen(navController = navController, studentId = studentId)
//        }
//
//        composable(
//            route = Screen.Report.route,
//            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
//            ReportScreen(navController = navController, studentId = studentId)
//        }
    }
}