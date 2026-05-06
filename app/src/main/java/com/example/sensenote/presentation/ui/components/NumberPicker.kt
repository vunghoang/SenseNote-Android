package com.example.sensenote.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NumberPicker(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange = 1..10
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            IconButton(
                onClick = { if (value > range.first) onValueChange(value - 1) },
                enabled = value > range.first
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Giảm",
                    modifier = Modifier.size(20.dp),
                    tint = if (value > range.first) Color.Black else Color.Gray
                )
            }

            Text(
                text = value.toString(),
                modifier = Modifier.padding(horizontal = 12.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            IconButton(
                onClick = { if (value < range.last) onValueChange(value + 1) },
                enabled = value < range.last
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tăng",
                    modifier = Modifier.size(20.dp),
                    tint = if (value < range.last) Color.Black else Color.Gray
                )
            }
        }
    }
}