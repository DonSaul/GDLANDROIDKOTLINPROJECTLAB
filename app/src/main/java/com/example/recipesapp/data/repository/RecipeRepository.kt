package com.example.recipesapp.data.repository

import com.example.recipesapp.model.RecipesArray
import retrofit2.Response

interface RecipeRepository {

    suspend fun getRandomRecipes(
        limitLicense: Boolean,
        tags: String,
        number: Int,
        apiKey: String
    ): Response<RecipesArray>
}