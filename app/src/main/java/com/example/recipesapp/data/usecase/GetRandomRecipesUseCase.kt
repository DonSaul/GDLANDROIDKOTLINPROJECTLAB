package com.example.recipesapp.data.usecase

import com.example.recipesapp.data.repository.RecipeRepository
import javax.inject.Inject

class GetRandomRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository
) {
    suspend operator fun invoke(
        limitLicense: Boolean,
        tags: String,
        number: Int,
        apiKey: String
    ) = recipesRepository.getRandomRecipes(limitLicense, tags, number, apiKey)
}