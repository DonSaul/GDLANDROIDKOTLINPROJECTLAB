package com.example.recipesapp.data.repository

import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import retrofit2.Response

interface RecipesInformationBulk {
    suspend fun getRecipesInformationBulk(
        ids: List<Long>,
        apiKey: String,
        includeNutrition: Boolean = false
    ): Response<List<Recipe>>
}