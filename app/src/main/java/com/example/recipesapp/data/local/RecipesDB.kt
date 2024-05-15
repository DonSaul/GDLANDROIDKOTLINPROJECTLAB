package com.example.recipesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipesapp.data.local.dao.FavoriteDao

import com.example.recipesapp.data.local.entities.FavoriteEntity


@Database(entities = [FavoriteEntity::class], version = 2)
abstract class RecipesDB: RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
}