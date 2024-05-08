package com.example.recipesapp.data.impl

import com.example.recipesapp.data.RecipesApi
import com.example.recipesapp.data.repository.RecipesSimilar
import com.example.recipesapp.model.SimilarRecipe
import retrofit2.Response
import javax.inject.Inject

class RecipesSimilarImpl @Inject constructor(private val api: RecipesApi) : RecipesSimilar {
    override suspend fun getSimilarRecipes(
        recipeId: Int,
        apiKey: String,
        number: Int,
        limitLicense: Boolean
    ): Response<List<SimilarRecipe>> {
        return api.getSimilarRecipes(recipeId, number, limitLicense, apiKey)
    }
}