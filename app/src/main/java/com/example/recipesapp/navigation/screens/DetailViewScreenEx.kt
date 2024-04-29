package com.example.recipesapp.navigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipesapp.viewModel.RecipeByIdViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.recipesapp.data.IdRecipe
import com.example.recipesapp.model.ProductMatch
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.WinePairing
import com.example.recipesapp.ui.theme.LightBrown
import com.example.recipesapp.viewModel.State
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import com.example.recipesapp.navigation.Screen

@Composable
@Preview
fun RecipeDetailPreview() {
    val recipe = Recipe(
        id = 1234,
        title = "Green enchiladas",
        image = "https://img.spoonacular.com/recipes/1098304-312x231.jpg",
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
        instructions = "Prueba",
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
        summary = "These are some delicious enchiladas...",
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
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailViewScreen(navController: NavController, id:Int){
    IdRecipe.idRecipe = id
    val viewModel: RecipeByIdViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.ChevronLeft, "",
            modifier = Modifier
                .size(30.dp)
                .clickable(
                    enabled = true,
                    onClickLabel = null,
                    role = Role.Button,
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }
                )
                .background(Color.Transparent, RoundedCornerShape(16.dp)),
            tint = Color.White
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            colors = CardDefaults.cardColors(
                //containerColor = MaterialTheme.colorScheme.primaryContainer,
                containerColor = LightBrown
            ),
        ) {
            when (uiState.value) {
                is State.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Text(text = "Loading")
                    }
                }

                is State.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Error")
                        Text(text = (uiState.value as State.Error).error)
                    }
                }

                is State.Success -> {
                    val recipe = (uiState.value as State.Success).data

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${recipe?.title}",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "${recipe?.summary}",
                            style = MaterialTheme.typography.bodySmall,
                            //modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    Column(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth().clip(RoundedCornerShape(16)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        AsyncImage(
                            model = recipe?.image,
                            contentScale = ContentScale.Crop,
                            contentDescription = recipe?.summary,
                            modifier = Modifier
                                .height(200.dp).width(300.dp)
                                .clip(RoundedCornerShape(16))

                        )
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Column(modifier = Modifier.padding(16.dp))
                        {
                            Text(
                                text = "Ingredients:",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                            Text(
                                text = "Lorem\nIpsum\nLatin",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            /*recipe.instructions.forEach { ingredient ->
                                Text(
                                    text = "° $ingredient",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }*/
                        }
                    }

                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Column(modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .weight(1f)){
                            Text(
                                text = "Procedure:",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                            Text(
                                text = "${recipe?.instructions}",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            /*recipe?.instructions.forEach{ instruction ->
                                Text(
                                    text = "${instruction}",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }*/
                        }
                    }
                    /*val data = (uiState.value as State.Success).data
                    Text(
                        text = "Recipes ${data?.id} \n ${data?.image}",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )*/
                    //ListRecipes(recipes = data.results, isLoading = false)

                }
            }
        }
    }
}