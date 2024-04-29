package com.example.recipesapp.data.repository

import com.example.recipesapp.model.RecipeSearch
import retrofit2.Response

interface RecipeSearchRepository {

    suspend fun getRecipeSearch1(
        apiKey: String,
        query: String
    ): Response<RecipeSearch>
}