package com.example.recipesapp.data.repository

import com.example.recipesapp.data.local.dao.SearchHistoryDao
import com.example.recipesapp.data.local.entities.SearchHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(private val searchHistoryDao: SearchHistoryDao){

    suspend fun addSearch(searchHistoryEntity: SearchHistoryEntity) = searchHistoryDao.insertSearchHistory(searchHistoryEntity)
    suspend fun getSearchHistory() = searchHistoryDao.getSearchHistory().flowOn(Dispatchers.IO).conflate()
}