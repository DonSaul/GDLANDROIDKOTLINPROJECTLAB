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
import com.example.recipesapp.model.Recipe


@Composable
fun FavoriteList(
    recipes: List<Recipe>,
    onRecipeClick: (Int) -> Unit,
    onDeleteFavorite: (Int) -> Unit
) {
    Text(
        text = "Favorites",
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
                SwipeItemFavorite(
                    recipe = item,
                    onDeleteFavorite = {
                        Log.i("deleteFavorite", item.id.toString())
                        onDeleteFavorite(item.id)
                    },
                    action = {
                        Log.i("debug", item.id.toString())
                        onRecipeClick(item.id)
                    }
                )
            }
        }
    }
}