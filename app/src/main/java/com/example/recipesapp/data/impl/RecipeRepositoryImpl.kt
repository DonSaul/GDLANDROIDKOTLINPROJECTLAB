package com.example.recipesapp.data.impl

import com.example.recipesapp.data.RecipesApi
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.utils.API_KEY
import retrofit2.Response
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(val api: RecipesApi) : RecipeRepository {
    override suspend fun getRandomRecipes(
        limitLicense: Boolean,
        tags: String,
        number: Int,
        apiKey: String
    ): Response<RecipesArray> {
        return api.getRandomRecipes(limitLicense, tags, number, apiKey = API_KEY)

    }
}