package com.example.sensenote.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ABCPicker(title: String, options: List<String>, onSelected: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = title)
        LazyRow {
            items(options) { option ->
                AssistChip(
                    onClick = { onSelected(option) },
                    label = { Text(option) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}