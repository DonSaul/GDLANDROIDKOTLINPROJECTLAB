package com.example.recipesapp.model

data class RecipeSearch(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)