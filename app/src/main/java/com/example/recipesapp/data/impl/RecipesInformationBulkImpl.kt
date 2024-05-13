package com.example.recipesapp.data.impl

import com.example.recipesapp.data.RetroFitService
import com.example.recipesapp.data.repository.RecipesInformationBulk
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import retrofit2.Response
import javax.inject.Inject

class RecipesInformationBulkImpl @Inject constructor(private val api: RetroFitService) : RecipesInformationBulk {
    // ...
    override suspend fun getRecipesInformationBulk(
        ids: List<Long>,
        apiKey: String,
        includeNutrition: Boolean
    ): Response<List<Recipe>> {
        val idString = ids.joinToString(",")
        return api.getRecipesInformationBulk(idString, apiKey, includeNutrition)
    }
}