package com.example.recipesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipesapp.data.local.entities.SeenRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeen(seenRecipeEntity: SeenRecipeEntity)

    @Query("SELECT * FROM seenRecipes WHERE recipeId = :recipeId")
    suspend fun getSeenRecipeById(recipeId: Int): SeenRecipeEntity?

    @Query("SELECT * FROM seenRecipes")
    fun getSeenRecipes(): Flow<List<SeenRecipeEntity>>

    @Query("DELETE FROM seenRecipes WHERE recipeId = :recipeId")
    suspend fun deleteSeenRecipeById(recipeId: Int)

    @Query("DELETE FROM seenRecipes")
    suspend fun deleteAllSeen()

}