package com.example.recipesapp.data.impl

import com.example.recipesapp.data.RetroFitService
import com.example.recipesapp.data.repository.RecipesSimilar
import com.example.recipesapp.model.SimilarRecipe
import retrofit2.Response

class RecipesSimilarImpl(private val api: RetroFitService) : RecipesSimilar {
    override suspend fun getSimilarRecipes(
        recipeId: Int,
        apiKey: String,
        number: Int,
        limitLicense: Boolean
    ): Response<List<SimilarRecipe>> {
        return api.getSimilarRecipes(recipeId, number, limitLicense, apiKey)
    }
}