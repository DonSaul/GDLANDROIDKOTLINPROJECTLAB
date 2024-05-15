package com.example.recipesapp.data.repository

import com.example.recipesapp.data.local.entities.SeenRecipeEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun getAllHistory(): Flow<List<SeenRecipeEntity>>
    suspend fun deleteAllHistory()
}