package com.example.recipesapp.components.recipes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesapp.model.Recipe


@Composable
fun FavoriteList(
    recipes: List<Recipe>,
    onRecipeClick: (Int) -> Unit,
    onDeleteFavorite: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
            //contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            items(recipes.size) { item ->
                SwipeItemFavorite(
                    recipe = recipes[item],
                    onDeleteFavorite = {
                        Log.i("deleteFavorite", recipes[item].id.toString())
                        onDeleteFavorite(recipes[item].id)
                    },
                    action = {
                        Log.i("debug", recipes[item].id.toString())
                        onRecipeClick(recipes[item].id)
                    }
                )
            }
        }
    }
}