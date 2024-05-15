package com.example.recipesapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.data.impl.FavoritesRepositoryImpl
import com.example.recipesapp.data.impl.HistoryRepositoryImpl
import com.example.recipesapp.data.impl.RecipesInformationBulkImpl
import com.example.recipesapp.data.local.RecipesDB
import com.example.recipesapp.data.local.dao.SeenDao
import com.example.recipesapp.data.local.entities.FavoriteEntity
import com.example.recipesapp.data.local.entities.SeenRecipeEntity
import com.example.recipesapp.data.repository.FavoritesRepository
import com.example.recipesapp.data.repository.HistoryRepository
import com.example.recipesapp.data.usecase.GetRecipesInformationBulkUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.utils.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        RecipesDB::class.java,
        "recipe_database"
    ).build()

    private val seenDao = db.seenDao()
    private val historyRepository: HistoryRepository = HistoryRepositoryImpl(seenDao)

    private val api = RetrofitServiceFactory.makeRetrofitService()
    private val recipesInformationBulkImpl = RecipesInformationBulkImpl(api)
    private val getRecipesInformationBulkUseCase = GetRecipesInformationBulkUseCase(recipesInformationBulkImpl)

    private val _history = MutableStateFlow<List<SeenRecipeEntity>>(emptyList())
    val history = _history.asStateFlow()

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    init {
        viewModelScope.launch {
            getAllHistory()
        }
    }



    fun deleteAllHistory() {
        viewModelScope.launch {
            historyRepository.deleteAllHistory()
        }
    }

    private fun getAllHistory() {
        viewModelScope.launch {
            historyRepository.getAllHistory().collect { historyList ->
                _history.value = historyList
                Log.d("HistoryViewModel", "History IDs: ${historyList.map { it.recipeId }}")
                fetchRecipesInformation(historyList.map { it.recipeId.toLong() })
            }
        }
    }
    private fun fetchRecipesInformation(ids: List<Long>) {
        viewModelScope.launch {
            try {
                Log.d("HistoryViewModel", "Fetching recipe information for IDs: $ids")
                _isLoading.value = true
                val recipes = getRecipesInformationBulkUseCase.invoke(ids, API_KEY)
                Log.d("HistoryViewModel", "Recipes response: $recipes")
                _recipes.value = recipes
            } catch (e: Exception) {
                Log.e("HistoryViewModel", "Error fetching recipe information", e)
                // Manejo de errores
            } finally {
                _isLoading.value = false
            }
        }
    }


}