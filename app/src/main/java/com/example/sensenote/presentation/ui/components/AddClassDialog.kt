package com.example.sensenote.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClassDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Int, Int, Int) -> Unit
) {
    var className by remember { mutableStateOf("") }
    var contextName by remember { mutableStateOf("") }

    // Giá trị mặc định cho các lựa chọn
    var numRows by remember { mutableIntStateOf(5) }
    var numCols by remember { mutableIntStateOf(4) }
    var seatsPerTable by remember { mutableIntStateOf(2) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Thiết lập lớp học mới") },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = className,
                    onValueChange = { className = it },
                    label = { Text("Tên lớp (Vd: 3A)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = contextName,
                    onValueChange = { contextName = it },
                    label = { Text("Tên bối cảnh (Vd: Toán 3A)") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Lựa chọn Số hàng và Số cột
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NumberDropdown(
                        label = "Số hàng",
                        options = (1..10).toList(),
                        selectedNumber = numRows,
                        onNumberSelected = { numRows = it },
                        modifier = Modifier.weight(1f)
                    )
                    NumberDropdown(
                        label = "Số cột",
                        options = (1..10).toList(),
                        selectedNumber = numCols,
                        onNumberSelected = { numCols = it },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Lựa chọn Số người mỗi bàn
                NumberDropdown(
                    label = "Số người mỗi bàn",
                    options = (1..6).toList(),
                    selectedNumber = seatsPerTable,
                    onNumberSelected = { seatsPerTable = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(className, contextName, numRows, numCols, seatsPerTable)
                },
                enabled = className.isNotBlank() && contextName.isNotBlank()
            ) {
                Text("Tạo lớp")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Hủy") }
        }
    )
}

/**
 * Thành phần Dropdown tùy chỉnh để chọn số
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberDropdown(
    label: String,
    options: List<Int>,
    selectedNumber: Int,
    onNumberSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedNumber.toString(),
            onValueChange = {},
            readOnly = true, // Không cho phép nhập từ bàn phím
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { number ->
                DropdownMenuItem(
                    text = { Text(text = number.toString()) },
                    onClick = {
                        onNumberSelected(number)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}