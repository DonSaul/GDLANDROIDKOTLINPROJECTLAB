package com.example.recipesapp.model

data class SimilarRecipe(
    val id: Int,
    val title: String,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String
)