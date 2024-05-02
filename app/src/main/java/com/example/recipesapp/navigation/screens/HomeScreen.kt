import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.recipesapp.components.recipes.SearchBar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

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

    Scaffold(
        topBar = {
            SearchBar(placeholder = "Search recipes...",
                action = { query -> viewModel.getSearchRecipe2(query) })
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
                    val dataRecommendations = (uiStateR as State.Success).data
                    Column(
                        modifier = Modifier
                            .background(LightBrown)
                            .padding(10.dp, 0.dp)
                    ) {
                        RecommendedRecipeList(dataRecommendations.recipes, onRecipeClick = { clickedRecipeId ->
                            onIdSelectedChange(clickedRecipeId)
                            navController.navigate(Screen.Detail.route)
                        })
                        RecipeList(data.results,
                            onRecipeClick = { clickedRecipeId ->
                                onIdSelectedChange(clickedRecipeId)
                                navController.navigate(Screen.Detail.route)
                            },
                            onAddFavorite = {
                                /* TODO: Favorite method */
                            }
                        )
                    }
                }
            }
        }
    }
}


