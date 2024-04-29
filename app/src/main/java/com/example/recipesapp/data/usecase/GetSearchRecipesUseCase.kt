
package com.example.recipesapp.data.usecase

import com.example.recipesapp.data.repository.RecipeSearchRepository
import com.example.recipesapp.model.RecipeSearch
import javax.inject.Inject

class GetSearchRecipesUseCase @Inject constructor(
    private val recipeSearchRepository: RecipeSearchRepository
) {
    suspend operator fun invoke(
        apiKey: String,
        query: String
    ): RecipeSearch {
        val response = recipeSearchRepository.getRecipeSearch1(apiKey = apiKey, query = query)
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
        return recipeSearchRepository.getRecipeSearch1(apiKey, query).body()!!
    }
}
