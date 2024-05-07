package com.example.recipesapp.data.repository

import com.example.recipesapp.data.local.dao.SearchHistoryDao
import com.example.recipesapp.data.local.entities.SearchHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface SearchHistoryRepository{

    suspend fun addSearch(searchHistoryEntity: SearchHistoryEntity): Unit
    suspend fun getSearchHistory(): Flow<List<SearchHistoryEntity>>
}