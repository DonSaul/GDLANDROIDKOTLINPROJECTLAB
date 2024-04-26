package com.example.recipesapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipesapp.model.Result

@Composable
fun ListRecipes(recipes: List<Result> , modifier: Modifier = Modifier) {
    LazyColumn () {
        itemsIndexed(recipes) { index, item ->
            RecipeCard(item, action = {})
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

    LazyColumn () {
        itemsIndexed(recipes) { index, item ->
            RecipeCard(recipes[index], action = {})
        }
    }
}