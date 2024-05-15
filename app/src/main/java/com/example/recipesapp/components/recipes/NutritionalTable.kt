package com.example.recipesapp.components.recipes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recipesapp.model.Nutrition

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    fontWeight: FontWeight?
) {
    Text(
        text = text,
        modifier = Modifier.border(width = 0.5.dp, color = Color.Black)
            .weight(weight)
            .padding(4.dp),
        fontWeight = fontWeight
    )
}
