package com.example.recipesapp.data

import androidx.lifecycle.viewModelScope
import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.utils.API_KEY
import com.example.recipesapp.viewModel.State
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroFitService {

    /*@GET("{ltype}")
    suspend fun listRecipes(
        @Path("ltype") type: String,
        @Query("apiKey") apiKey: String,
        @Query("query") query: String?
    ): RecipeSearch*/

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
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetroFitService {
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetroFitService::class.java)
    }
}


/*suspend fun getRecipe2(query: String? = "Taco"): RecipeSearch{
        val service = RetrofitServiceFactory.makeRetrofitService()
        val list =
            service.listRecipes("complexSearch", API_KEY, query)
    return list
    }*/


