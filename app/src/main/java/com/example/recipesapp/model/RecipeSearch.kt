package com.example.recipesapp.model


data class Results(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String,

)

data class RecipeSearch(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)

data class RecipeSearchList(
    val recipes: List<RecipeSearch>
)