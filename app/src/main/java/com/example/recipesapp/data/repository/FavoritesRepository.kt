package com.example.recipesapp.data.repository

import com.example.recipesapp.data.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun getAllFavorites(): Flow<List<FavoriteEntity>>
    suspend fun deleteFavorite(recipeId: Int)
}