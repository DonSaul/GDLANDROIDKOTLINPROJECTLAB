package com.example.recipesapp.data.impl

import com.example.recipesapp.data.local.dao.FavoriteDao
import com.example.recipesapp.data.local.entities.FavoriteEntity
import com.example.recipesapp.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoritesRepository {

    override suspend fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun deleteFavorite(recipeId: Int) {
        favoriteDao.deleteFavoriteById(recipeId)
    }

    override fun isInFavorite(recipeId: Int): Flow<Boolean> {
        return favoriteDao.getFavoriteById(recipeId)
    }
}