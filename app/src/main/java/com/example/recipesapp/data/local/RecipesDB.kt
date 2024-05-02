package com.example.recipesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipesapp.data.local.dao.RecipeDao
import com.example.recipesapp.data.local.entities.Recipe

@TypeConverters(value = [RoomTypeConverters::class])
@Database(entities = [Recipe::class], version = 1)
abstract class RecipesDB: RoomDatabase(){
    abstract fun recipeDao(): RecipeDao
}