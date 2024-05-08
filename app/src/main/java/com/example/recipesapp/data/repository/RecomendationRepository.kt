package com.example.recipesapp.data.repository

import com.example.recipesapp.model.SimilarRecipe
import retrofit2.Response

interface RecipesSimilar {
    suspend fun getSimilarRecipes(
            recipeId: Int,
            apiKey: String,
            number: Int = 10,
            limitLicense: Boolean = true
        ): Response<List<SimilarRecipe>>
    }
