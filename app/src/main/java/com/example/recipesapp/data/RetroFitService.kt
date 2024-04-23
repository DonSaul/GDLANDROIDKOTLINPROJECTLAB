package com.example.recipesapp.data

import com.example.recipesapp.model.RecipeSearch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  RetroFitService{

    @GET("{ltype}")
    suspend fun listRecipes(
        @Path("ltype") type: String,
        @Query("apiKey") apiKey: String,
        @Query("query") query: String
    ): RecipeSearch
}

object RetrofitServiceFactory{
    fun makeRetrofitService(): RetroFitService{
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetroFitService::class.java)
    }
}