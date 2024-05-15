package com.example.recipesapp.data.usecase

import android.util.Log
import com.example.recipesapp.data.impl.RecipesSimilarImpl
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.model.SimilarRecipe
import javax.inject.Inject

class GetSimilarRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesSimilarImpl
) {
    suspend operator fun invoke(recipeId: Int, apiKey: String): List<SimilarRecipe> {
        Log.d("GetSimilarRecipesUseCase", "Recipe ID: $recipeId")
        val response = recipesRepository.getSimilarRecipes(recipeId, apiKey)
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
        return recipesRepository.getSimilarRecipes(recipeId, apiKey).body()!!
    }
}