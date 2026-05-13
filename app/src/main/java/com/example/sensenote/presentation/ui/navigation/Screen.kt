package com.example.sensenote.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")

    // SeatMap: Giữ nguyên 5 tham số
    object SeatMap : Screen("seat_map/{contextId}/{className}/{rows}/{cols}/{seatsPerTable}") {
        fun createRoute(contextId: Int, className: String, rows: Int, cols: Int, seatsPerTable: Int) =
            "seat_map/$contextId/$className/$rows/$cols/$seatsPerTable"
    }

    // Cập nhật StudentList: Thêm seatsPerTable
    object StudentList : Screen("student_list/{contextId}/{className}/{rows}/{cols}/{seatsPerTable}") {
        fun createRoute(contextId: Int, className: String, rows: Int, cols: Int, seatsPerTable: Int) =
            "student_list/$contextId/$className/$rows/$cols/$seatsPerTable"
    }

    // Cập nhật AddStudent: Thêm className và seatsPerTable
    object AddStudent : Screen("add_student/{contextId}/{className}/{rows}/{cols}/{seatsPerTable}") {
        fun createRoute(contextId: Int, className: String, rows: Int, cols: Int, seatsPerTable: Int) =
            "add_student/$contextId/$className/$rows/$cols/$seatsPerTable"
    }

    object StudentDetail : Screen("student_detail/{studentId}") {
        fun createRoute(studentId: String) = "student_detail/$studentId"
    }
    object BehaviorSetting : Screen("behavior_setting")
    object Diary : Screen("diary")
    object Report : Screen("report/{studentId}") {
        fun createRoute(studentId: String) = "report/$studentId"
    }
}