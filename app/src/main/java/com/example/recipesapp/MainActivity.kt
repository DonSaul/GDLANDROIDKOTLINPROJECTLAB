package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.recipesapp.components.recipeCard
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.model.Result
import com.example.recipesapp.ui.theme.RecipesAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        service = Call API service

        Params String
        type: Type of search in API (complexSearch, findByNutrients, findByIngredients etc.)
        apiKey: 178d63d1ecc749af92d4180120d05054 (only 150 call a day... Replace with new account if more needed)
        query: Type of search ex. "Tacos" (only works with complexSearch)
         */

        val service = RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            val recipes = service.listRecipes("complexSearch",
                "178d63d1ecc749af92d4180120d05054",
                "Taco")
            println(recipes)

            val recipeId = 716429
            val apiKey = "178d63d1ecc749af92d4180120d05054"
            val recipe = service.getRecipeInformation(recipeId, apiKey)
            println(recipe)
        }

        //ejemplo
        val recipe = Result(
            id = 1,
            image = "https://img.spoonacular.com/recipes/662744-312x231.jpg",
            imageType = "jpg",
            title = "Taco Egg Roll"
        )

        setContent {
            RecipesAppTheme {
                recipeCard(recipe = recipe, action = {})
            }
        }
    }
}