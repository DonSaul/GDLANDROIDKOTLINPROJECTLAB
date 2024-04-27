package com.example.recipesapp.components

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
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.model.Result

@Composable
fun ListRecipes(recipes: List<Result>, modifier: Modifier = Modifier, isLoading: Boolean) {
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
                    RecipeCard(item, action = {})
                }
            }
        }
    }
}

@Preview
@Composable
fun ListRecipesPreview(modifier: Modifier = Modifier) {
    val recipes = mutableListOf<Result>()

    // Recipe 1: Spaghetti Carbonara
    val spaghettiCarbonara = Result(
        id = 1,
        image = "https://img.spoonacular.com/recipes/662744-312x231.jpg",
        imageType = "jpg",
        title = "Spaghetti Carbonara"
    )
    recipes.add(spaghettiCarbonara)

    // Recipe 2: Vegetable Stir-Fry
    val vegetableStirFry = Result(
        id = 2,
        image = "https://img.spoonacular.com/recipes/662744-312x231.jpg",
        imageType = "jpg",
        title = "Vegetable Stir-Fry"
    )
    recipes.add(vegetableStirFry)

    // Recipe 3: Grilled Salmon with Asparagus
    val grilledSalmonAsparagus = Result(
        id = 3,
        image = "https://img.spoonacular.com/recipes/662744-312x231.jpg",
        imageType = "jpg",
        title = "Grilled Salmon with Asparagus"
    )
    recipes.add(grilledSalmonAsparagus)

    // Recipe 4: Mushroom Risotto
    val mushroomRisotto = Result(
        id = 4,
        image = "https://img.spoonacular.com/recipes/662744-312x231.jpg",
        imageType = "jpg",
        title = "Mushroom Risotto"
    )
    recipes.add(mushroomRisotto)

    LazyColumn() {
        itemsIndexed(recipes) { index, item ->
            RecipeCard(recipes[index], action = {})
        }
    }
}