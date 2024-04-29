package com.example.recipesapp.navigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.recipesapp.viewModel.RecipeByIdViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipesapp.components.ListRecipes
import com.example.recipesapp.data.IdRecipe
import com.example.recipesapp.viewModel.State


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailViewScreenEx(id:Int){
    IdRecipe.idRecipe = id
    val viewModel: RecipeByIdViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    //viewModel.getRecipeById(id)

    //val data = viewModel.state.collectAsState().value

    //data.
    Column(modifier = Modifier.width(900.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
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
                val data = (uiState.value as State.Success).data
                Text(
                    text = "Recipes ${data?.id} \n ${data?.image}",
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                //ListRecipes(recipes = data.results, isLoading = false)

            }
        }
    }
}