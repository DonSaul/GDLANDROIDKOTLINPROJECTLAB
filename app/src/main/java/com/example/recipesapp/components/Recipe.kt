package com.example.recipesapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipesapp.model.ProductMatch
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.model.WinePairing



@Composable
@Preview
fun RecipeDetailPreview() {
    val recipe = Recipe(
        id = 123,
        title = "Delicious Pasta Recipe",
        image = "https://example.com/pasta.jpg",
        imageType = "jpg",
        servings = 4,
        readyInMinutes = 30,
        license = "Some License",
        sourceName = "Cooking Magazine",
        sourceUrl = "https://example.com/pasta-recipe",
        spoonacularSourceUrl = "https://spoonacular.com/recipes/delicious-pasta-recipe",
        aggregateLikes = 100,
        healthScore = 9.5,
        spoonacularScore = 95.0,
        pricePerServing = 2.5,
        analyzedInstructions = listOf(
        ),
        cheap = false,
        creditsText = "Some credits",
        cuisines = listOf("Italian"),
        dairyFree = false,
        diets = listOf("Healthy"),
        gaps = "Some gaps",
        glutenFree = false,
        instructions = "Lorem",
        ketogenic = false,
        lowFodmap = false,
        occasions = listOf("Dinner"),
        sustainable = true,
        vegan = false,
        vegetarian = true,
        veryHealthy = true,
        veryPopular = true,
        whole30 = false,
        weightWatcherSmartPoints = 7,
        dishTypes = listOf("Pasta"),
        extendedIngredients = listOf(
        ),
        summary = "This is a delicious pasta recipe...",
        winePairing = WinePairing(
            pairedWines = listOf("Red Wine", "White Wine"),
            pairingText = "This recipe pairs well with red or white wine.",
            productMatches = listOf(
                ProductMatch(
                    id = 1,
                    title = "Red Wine",
                    description = "A delicious red wine.",
                    price = "$20",
                    imageUrl = "https://example.com/red-wine.jpg",
                    averageRating = 4.5,
                    ratingCount = 100,
                    score = 95.0,
                    link = "https://example.com/red-wine"
                ),
                ProductMatch(
                    id = 2,
                    title = "White Wine",
                    description = "A refreshing white wine.",
                    price = "$18",
                    imageUrl = "https://example.com/white-wine.jpg",
                    averageRating = 4.3,
                    ratingCount = 80,
                    score = 92.0,
                    link = "https://example.com/white-wine"
                )
            )
    ))
    RecipeDetail(recipe = recipe)
}
@Composable
fun RecipeDetail(recipe: Recipe, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = recipe.summary,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Ingredients:",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            recipe.instructions.forEach { ingredient ->
                Text(
                    text = "- $ingredient",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            Text(
                text = "Instructions:",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            recipe.instructions.forEachIndexed { index, instruction ->
                Text(
                    text = "${index + 1}. $instruction",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
