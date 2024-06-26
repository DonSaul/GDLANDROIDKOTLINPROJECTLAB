package com.example.recipesapp.data

import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.model.SimilarRecipe
import com.example.recipesapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {

    @GET("complexSearch")
    suspend fun listRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("diet") diet: String
    ): Response<RecipeSearch>

    @GET("{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") recipeId: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean = true
    ): Response<Recipe>

    @GET("random")
    suspend fun getRandomRecipes(
        @Query("limitLicense") limitLicense: Boolean,
        @Query("tags") tags: String,
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Response<RecipesArray>

    @GET("informationBulk")
    suspend fun getRecipesInformationBulk(
        @Query("ids") ids: String,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean = false
    ): Response<List<Recipe>>

    @GET("{id}/similar")
    suspend fun getSimilarRecipes(
        @Path("id") recipeId: Int,
        @Query("number") number: Int = 10,
        @Query("limitLicense") limitLicense: Boolean = false,
        @Query("apiKey") apiKey: String
    ): Response<List<SimilarRecipe>>
}