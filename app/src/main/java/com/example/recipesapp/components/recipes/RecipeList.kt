package com.example.recipesapp.components.recipes

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesapp.model.Result


@Composable
fun RecipeList(
    recipes: List<Result>,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    idSelected: Int,
    onRecipeClick: (Int) -> Unit
) {
    Text(
        text = "Recipes",
        style = MaterialTheme.typography.titleMedium,
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {

        LazyColumn() {
            itemsIndexed(recipes) { _, item ->
                RecipeCard(item, action = {
                    Log.i("debug", item.id.toString())
                    onRecipeClick(item.id)
                })
            }
        }
    }
}
