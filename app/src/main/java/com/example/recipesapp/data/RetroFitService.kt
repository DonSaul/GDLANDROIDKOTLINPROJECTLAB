package com.example.recipesapp.data

import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroFitService {

    @GET("{ltype}")
    suspend fun listRecipes(
        @Path("ltype") type: String,
        @Query("apiKey") apiKey: String,
        @Query("query") query: String
    ): RecipeSearch

    @GET("{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") recipeId: Int,
        @Query("apiKey") apiKey: String
    ): Recipe

    @GET("random")
    suspend fun getRandomRecipes(
        @Query("limitLicense") limitLicense: Boolean,
        @Query("tags") tags: String,
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): RecipesArray

    @GET("informationBulk")
    suspend fun getRecipesInformationBulk(
        @Query("ids") ids: String,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean = false
    ): Response<List<Recipe>>
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetroFitService {
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetroFitService::class.java)
    }
}