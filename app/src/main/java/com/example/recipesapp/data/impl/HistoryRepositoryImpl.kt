package com.example.recipesapp.data.impl

import com.example.recipesapp.data.local.dao.SeenDao
import com.example.recipesapp.data.local.entities.SeenRecipeEntity
import com.example.recipesapp.data.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(private val seenDao: SeenDao):HistoryRepository {
    override suspend fun getAllHistory(): Flow<List<SeenRecipeEntity>> {
        return seenDao.getSeenRecipes()
    }

    override suspend fun deleteAllHistory() {
        seenDao.deleteAllSeen()
    }
}