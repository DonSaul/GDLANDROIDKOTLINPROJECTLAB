package com.example.recipesapp.components.recipes

import RecommendedRecipe
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesapp.model.Result

@Composable
fun RecommendedRecipeList(recipes: List<Result>, onRecipeClick: (Int) -> Unit) {
    Text(
        text = "Recomendations",
        style = MaterialTheme.typography.titleMedium,
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow {
            items(recipes) {
                RecommendedRecipe(it, action = {
                    Log.i("debug", it.id.toString())
                    onRecipeClick(it.id)
                })
            }
        }

    }
}