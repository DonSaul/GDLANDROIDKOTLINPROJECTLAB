package com.example.recipesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipesapp.data.local.dao.FavoriteDao
import com.example.recipesapp.data.local.dao.SearchHistoryDao

import com.example.recipesapp.data.local.entities.FavoriteEntity
import com.example.recipesapp.data.local.entities.SearchHistoryEntity


@Database(entities = [FavoriteEntity::class, SearchHistoryEntity::class], version = 2)
abstract class RecipesDB: RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}