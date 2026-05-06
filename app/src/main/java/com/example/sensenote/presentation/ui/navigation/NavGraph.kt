package com.example.sensenote.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sensenote.presentation.ui.screens.HomeScreen
import com.example.sensenote.presentation.ui.screens.StudentListScreen
import com.example.sensenote.presentation.ui.screens.LoginScreen // Đảm bảo bạn đã import LoginScreen
import com.example.sensenote.presentation.ui.screens.ReportScreen
import com.example.sensenote.presentation.ui.screens.SeatMapScreen
import com.example.sensenote.presentation.ui.screens.StudentDetailScreen

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

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                onClassClick = { classId ->
                    navController.navigate(Screen.SeatMap.route)
                }
            )
        }

        composable(Screen.SeatMap.route) {
            SeatMapScreen(navController = navController)
        }

        composable(Screen.StudentList.route) {
            StudentListScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.StudentDetail.route,
            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
            StudentDetailScreen(navController = navController, studentId = studentId)
        }

        composable(
            route = Screen.Report.route,
            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
            ReportScreen(navController = navController, studentId = studentId)
        }
    }
}