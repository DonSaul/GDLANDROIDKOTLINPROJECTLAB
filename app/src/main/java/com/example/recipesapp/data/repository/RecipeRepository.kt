package com.example.recipesapp.data.repository

import com.example.recipesapp.model.RecipesArray

interface RecipeRepository {

    suspend fun getRandomRecipes(
        limitLicense: Boolean,
        tags: String,
        number: Int,
        apiKey: String
    ): RecipesArray
}