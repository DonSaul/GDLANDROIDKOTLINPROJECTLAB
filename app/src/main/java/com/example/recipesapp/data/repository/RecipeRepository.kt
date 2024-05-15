package com.example.recipesapp.data.repository

import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.model.SimilarRecipe
import retrofit2.Response

interface RecipeRepository {

    suspend fun getRandomRecipes(
        limitLicense: Boolean,
        tags: String,
        number: Int,
        apiKey: String
    ): Response<RecipesArray>

    suspend fun getRecipeById(recipeId: Int, apiKey: String): Response<Recipe>

}