package com.example.sensenote.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object SeatMap : Screen("seat_map")
    object StudentList : Screen("student_list")

    object StudentDetail : Screen("student_detail/{studentId}") {
        fun createRoute(studentId: String) = "student_detail/$studentId"
    }

    object Report : Screen("report/{studentId}") {
        fun createRoute(studentId: String) = "report/$studentId"
    }

    object Diary : Screen("diary")
}