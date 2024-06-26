package com.example.recipesapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val recipeId: Int
)

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val search:String
)

@Entity(tableName = "seenRecipes")
data class SeenRecipeEntity(
    @PrimaryKey
    val recipeId: Int,
    val date: Date
)