package com.example.sensenote.presentation.ui.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.sensenote.domain.model.CategoryType
//import androidx.compose.material3.ExperimentalMaterial3Api
//
//@Composable
//fun BehaviorLogScreen(studentName: String, onSaveLog: (String, String, String) -> Unit) {
//    var antecedent by remember { mutableStateOf("") }
//    var behavior by remember { mutableStateOf("") }
//    var consequence by remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = { CenterAlignedTopAppBar(title = { Text("Ghi nhận hành vi: $studentName") }) }
//    ) { padding ->
//        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
//            Text("Mô hình ABC", style = MaterialTheme.typography.titleMedium)
//
//            ABCInputSection("Tiền đề (Antecedent)", antecedent) { antecedent = it }
//            ABCInputSection("Hành vi (Behavior)", behavior) { behavior = it }
//            ABCInputSection("Hệ quả (Consequence)", consequence) { consequence = it }
//
//            Spacer(modifier = Modifier.weight(1f))
//            Button(
//                onClick = { onSaveLog(antecedent, behavior, consequence) },
//                modifier = Modifier.fillMaxWidth(),
//                enabled = antecedent.isNotBlank() && behavior.isNotBlank() && consequence.isNotBlank()
//            ) {
//                Text("Lưu nhật ký")
//            }
//        }
//    }
//}
//
//@Composable
//fun ABCInputSection(label: String, value: String, onValueChange: (String) -> Unit) {
//    Column(modifier = Modifier.padding(vertical = 8.dp)) {
//        Text(label, style = MaterialTheme.typography.bodySmall)
//        OutlinedTextField(
//            value = value,
//            onValueChange = onValueChange,
//            modifier = Modifier.fillMaxWidth(),
//            placeholder = { Text("Chọn hoặc nhập nội dung...") }
//        )
//    }
//}