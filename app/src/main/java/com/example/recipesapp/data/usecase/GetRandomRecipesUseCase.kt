package com.example.recipesapp.data.usecase

import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.model.RecipesArray
import javax.inject.Inject

class GetRandomRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository
) {
    suspend operator fun invoke(
        limitLicense: Boolean,
        tags: String,
        number: Int,
        apiKey: String
    ): RecipesArray {
        val response = recipesRepository.getRandomRecipes(limitLicense, tags, number, apiKey)
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
        return recipesRepository.getRandomRecipes(limitLicense, tags, number, apiKey).body()!!
    }
}