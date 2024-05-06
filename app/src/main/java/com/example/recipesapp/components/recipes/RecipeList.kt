package com.example.recipesapp.components.recipes

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    recipes: List<Result>, onRecipeClick: (Int) -> Unit,
    onAddFavorite: (Int) -> Unit
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

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            itemsIndexed(recipes) { _, item ->
                SwipeItem(item,
                    onAddFavorite = {
                        Log.i("favorite", item.id.toString())
                        // Temporal method
                        onAddFavorite(item.id)
                        /* TODO : Add favorite method db */

                    },
                    action = {
                        Log.i("debug", item.id.toString())
                        onRecipeClick(item.id)
                    })
            }
        }
    }
}
