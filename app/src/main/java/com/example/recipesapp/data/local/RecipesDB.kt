package com.example.recipesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipesapp.data.local.dao.FavoriteDao
import com.example.recipesapp.data.local.dao.SeenDao

import com.example.recipesapp.data.local.entities.FavoriteEntity
import com.example.recipesapp.data.local.entities.SeenRecipeEntity


@Database(entities = [FavoriteEntity::class,SeenRecipeEntity::class], version = 2)
abstract class RecipesDB: RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao

    abstract fun seenDao(): SeenDao
}
