package com.example.recipesapp.data.local.dao

import androidx.room.*
import com.example.recipesapp.data.local.entities.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    fun getAllFavoritesRecipes(): Flow<List<Recipe>>
}