package com.example.recipesapp.navigation.screens

import FavoritesViewModel
import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.components.common.BottomNavigation
import com.example.recipesapp.components.recipes.TableCell
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.utils.getEquipment
import com.example.recipesapp.utils.getIngredients
import com.example.recipesapp.utils.*
import com.example.recipesapp.utils.htmlToAnnotatedString

@Composable
@Preview
fun RecipeDetailPreview() {
    /*val recipe = Recipe(
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
                ), ProductMatch(
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
    )*/
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailViewScreen(navController: NavController, id: Int) {
    IdRecipe.idRecipe = id
    val viewModel: RecipeByIdViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    val context = LocalContext.current
    val favViewModel: FavoritesViewModel = remember { FavoritesViewModel(context.applicationContext as Application) }

    val similarRecipes = viewModel.similarRecipes.collectAsState().value

    viewModel.addSeen(IdRecipe.idRecipe)
    val isFav by favViewModel.isFavoriteById(IdRecipe.idRecipe).collectAsState(initial = false)
    var isFavorite by remember {
        mutableStateOf(
            isFav
        )
    }
    isFavorite = isFav

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Detail recipe")},

                modifier = Modifier.background(LightBrown),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                        )
                    }
                },
                actions = {IconButton(onClick = {
                    if(isFavorite){
                        favViewModel.deleteFavorite(IdRecipe.idRecipe)
                        Toast.makeText(context,"Removed from Favorites",Toast.LENGTH_SHORT).show()
                    }

                    else{
                        favViewModel.addFav(IdRecipe.idRecipe)
                        Toast.makeText(context,"Added to Favorites",Toast.LENGTH_SHORT).show()
                    }

                }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF0E9E0)
                ))
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
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
                        modifier = Modifier
                            .background(LightBrown)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MainAnimation(
                            modifier = Modifier
                                .width(400.dp)
                                .height(250.dp)
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .align(alignment = Alignment.CenterHorizontally),
                            image = R.raw.re
                        )
                    }
                }

                is State.Error -> {
                    Column(
                        modifier = Modifier
                            .background(LightBrown)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Error", color = Color.White)
                        Text(text = (uiState.value as State.Error).error, color = Color.White)
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
                        val summaryText = htmlToAnnotatedString(recipe?.summary.toString())
                        Text(
                            text = summaryText,
                            style = MaterialTheme.typography.bodySmall,
                            //modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = recipe?.image,
                            contentScale = ContentScale.Crop,
                            contentDescription = recipe?.summary,
                            modifier = Modifier
                                .height(200.dp)
                                .width(300.dp)
                                .clip(RoundedCornerShape(16))

                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = "Ingredients",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                            val ingrdnt = getIngredients(recipe?.nutrition?.ingredients)
                            Text(
                                text = ingrdnt,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = "Equipment",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                            val equipment = getEquipment(recipe?.analyzedInstructions)
                            Text(
                                text = equipment,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Text(
                                text = "Procedure",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                            Divider(modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, top = 10.dp), color = Color.Black)
                            val summaryInstructions = htmlToAnnotatedString(recipe?.instructions.toString())
                            Text(
                                text = summaryInstructions,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Text(
                                text = "Nutritional information",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            )
                            Divider(modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, top = 10.dp), color = Color.Black)
                            val nutriName = getNutrientName(recipe?.nutrition)
                            val nutriAmount = getNutrientAmount(recipe?.nutrition)
                            val nutriUnit = getNutrientUnits(recipe?.nutrition)

                            Row( Modifier.fillMaxWidth()) {
                                TableCell(text = "Concept", weight = 3f, fontWeight = FontWeight.Bold)
                                TableCell(text = "Quantity", weight = 1.5f, fontWeight = FontWeight.Bold)
                                TableCell(text = "Units", weight = 1f, fontWeight = FontWeight.Bold)
                            }
                            Row( Modifier.fillMaxWidth()) {
                                TableCell(text = nutriName, weight = 3f, null)
                                TableCell(text = nutriAmount, weight = 1.5f, null)
                                TableCell(text = nutriUnit, weight = 1f, null)
                            }
                        }
                    }


                    if (similarRecipes.isNotEmpty()) {
                        Text(
                            text = "Similar Recipes",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                        )
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(similarRecipes) { similarRecipe ->
                                Column(
                                    modifier = Modifier
                                        .width(200.dp)
                                        .clickable {
                                            navController.navigate("detail_screen/${similarRecipe.id}")
                                    },
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        model = similarRecipe.image,
                                        contentDescription = similarRecipe.title,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(200.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )
                                    Text(
                                        text = similarRecipe.title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(top = 8.dp),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
