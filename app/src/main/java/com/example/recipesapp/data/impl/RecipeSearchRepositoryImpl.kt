package com.example.recipesapp.data.impl

import com.example.recipesapp.data.RecipesApi
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.data.repository.RecipeSearchRepository
import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.model.RecipeSearchList
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.utils.API_KEY
import retrofit2.Response
import javax.inject.Inject

class RecipeSearchRepositoryImpl @Inject constructor(val api: RecipesApi) : RecipeSearchRepository {
    override suspend fun getRecipeSearch1(
        apiKey: String,
        query: String
    ): Response<RecipeSearch> {
        return api.listRecipes(apiKey = apiKey,query = query)
    }
}