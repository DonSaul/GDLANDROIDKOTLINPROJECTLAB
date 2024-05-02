package com.example.recipesapp.data.repository

import com.example.recipesapp.data.local.dao.RecipeDao
import com.example.recipesapp.data.local.entities.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalRecipesRepository @Inject constructor(private val recipeDatabaseDao: RecipeDao) {
    suspend fun addRecipe(recipe: Recipe) = recipeDatabaseDao.insertRecipe(recipe)

    suspend fun deleteRecipe(recipe: Recipe) = recipeDatabaseDao.deleteRecipe(recipe)

    fun getAllRecipes() = recipeDatabaseDao.getAllFavoritesRecipes().flowOn(Dispatchers.IO).conflate()
}