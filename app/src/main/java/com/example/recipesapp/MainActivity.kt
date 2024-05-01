package com.example.recipesapp

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.components.ListRecipes
import com.example.recipesapp.components.RecipeCard
import com.example.recipesapp.components.RecipeDetail
import com.example.recipesapp.components.TextField
import com.example.recipesapp.data.IdRecipe
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.model.Result
import com.example.recipesapp.navigation.SetUpNavGraph
import com.example.recipesapp.ui.theme.RecipesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            RecipesAppTheme {
                SetUpNavGraph(navController = navController)
            }
        }
    }
}
