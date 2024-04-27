package com.example.recipesapp

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.recipesapp.components.ListRecipes
import com.example.recipesapp.components.RecipeCard
import com.example.recipesapp.components.TextField
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.model.Result
import com.example.recipesapp.ui.theme.RecipesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipesState =  mutableStateOf<List<Result>>(emptyList())
        var isLoading = mutableStateOf(true)
        val service = RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            val recipes = service.listRecipes(
                "complexSearch",
                "178d63d1ecc749af92d4180120d05054",
                "Taco"
            )
            delay(1500)
            recipesState.value = recipes.results.toList()
            isLoading.value = false
        }

        setContent {
            RecipesAppTheme {
                HomeScreen()
            }
        }
    }
}