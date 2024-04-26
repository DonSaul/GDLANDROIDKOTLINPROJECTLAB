package com.example.recipesapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipesapp.model.Result

@Composable
fun ListRecipes(recipes: List<Result> , modifier: Modifier = Modifier) {

    LazyColumn () {
        itemsIndexed(recipes) { index, item ->
            RecipeCard(recipes[index], action = {})
        }
    }
}