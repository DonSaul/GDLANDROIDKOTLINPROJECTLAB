package com.example.recipesapp.data.usecase

import android.util.Log
import com.example.recipesapp.data.impl.RecipesInformationBulkImpl
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import javax.inject.Inject


class GetRecipesInformationBulkUseCase @Inject constructor(
    private val recipesRepository: RecipesInformationBulkImpl
) {
    suspend operator fun invoke(ids: List<Long>, apiKey: String): List<Recipe> {
        Log.d("GetRecipesInformationBulkUseCase", "IDs: $ids")
        val response = recipesRepository.getRecipesInformationBulk(ids, apiKey)
        Log.d("GetRecipesInformationBulkUseCase", "IDs: ${response.body()}")
        if(response.body() == null){
            if(response.code() == 404){
                throw Exception("No recipes found")
            } else if(response.code() == 500){
                throw Exception("Server Error")
            } else if(response.code() == 401){
                throw Exception("Unathorized")
            } else if(response.code() == 400){
                throw Exception("Bad request")
            } else {
                throw Exception("No recipes found")
            }
        }
        return response.body()!!
    }
}