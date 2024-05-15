package com.example.recipesapp.data.impl

import com.example.recipesapp.data.local.dao.SearchHistoryDao
import com.example.recipesapp.data.local.entities.SearchHistoryEntity
import com.example.recipesapp.data.repository.SearchHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchHistoryImpl @Inject constructor(private val searchHistoryDao: SearchHistoryDao): SearchHistoryRepository{

    override suspend fun addSearch(searchHistoryEntity: SearchHistoryEntity) = searchHistoryDao.insertSearchHistory(searchHistoryEntity)
    override suspend fun getSearchHistory() = searchHistoryDao.getSearchHistory().flowOn(Dispatchers.IO).conflate()
}

