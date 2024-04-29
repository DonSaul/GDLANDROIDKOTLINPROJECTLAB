package com.example.recipesapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.Result

@Composable
fun ListRecipes(
    recipes: List<Result>,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    idSelected: Int,
    onRecipeClick: (Int) -> Unit // Click listener for recipe cards
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                MainAnimation(
                    modifier = Modifier
                        .width(250.dp)
                        .height(250.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .align(alignment = Alignment.Center), image = R.raw.re
                )
            }
        } else {
            LazyColumn() {
                itemsIndexed(recipes) { index, item ->
                    RecipeCard(item, action = {
                        Log.i("debug", item.id.toString())
                        onRecipeClick(item.id)
                    })
                }
            }
        }
    }
}