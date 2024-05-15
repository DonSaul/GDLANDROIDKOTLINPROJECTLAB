import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.viewModel.RecipeViewModel
import com.example.recipesapp.viewModel.State
import androidx.compose.ui.graphics.Color
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.components.recipes.RecipeList
import com.example.recipesapp.components.recipes.RecommendedRecipeList
import com.example.recipesapp.ui.theme.LightBrown
//import com.example.recipesapp.components.recipes.SearchBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.example.recipesapp.components.common.BottomNavigation
import com.example.recipesapp.components.recipes.SearchBarApp
import com.example.recipesapp.data.lastScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    idSelected: Int,
    onIdSelectedChange: (Int) -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    val uiStateR = viewModel.stateR.collectAsState().value
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    lastScreen.lastScreen = Screen.Home.route

    Scaffold(
        topBar = {
            SearchBarApp(placeholder = "Search recipes...",
                action = { query, query2 -> viewModel.getSearchRecipes(query, query2) })
        },
        bottomBar = {
            BottomNavigation(navController)
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                    val data = (uiState.value as State.Success).data
                    val recommendedRecipes = when (val currentRecommendationState = uiStateR) {
                        is State.Success -> currentRecommendationState.data.recipes
                        else -> emptyList()
                    }
                    Column(
                        modifier = Modifier
                            .background(LightBrown)
                            .padding(10.dp, 0.dp)
                    ) {
                        if (recommendedRecipes.isNotEmpty()) {
                            RecommendedRecipeList(
                                recommendedRecipes,
                                onRecipeClick = { clickedRecipeId ->
                                    onIdSelectedChange(clickedRecipeId)
                                    navController.navigate(Screen.Detail.route)
                                })
                        }


                        RecipeList(data.results,
                            onRecipeClick = { clickedRecipeId ->
                                onIdSelectedChange(clickedRecipeId)
                                navController.navigate(Screen.Detail.route)
                            },
                            onAddFavorite = { recipeId ->
                                viewModel.addFavorite(recipeId)
                            }
                        )
                    }
                }
            }
        }
    }
}

enum class ScreenEnum(val route: String, val icon: ImageVector, val title: String) {
    Home("home", Icons.Filled.Home, Screen.Home.title),
    Favorites("favorites", Icons.Filled.Favorite, "Favorites"),
    Settings("settings", Icons.Filled.Settings, "Settings")
}




