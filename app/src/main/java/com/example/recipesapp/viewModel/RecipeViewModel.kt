package com.example.recipesapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.usecase.GetRandomRecipesUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    val getRandomRecipesUseCase: GetRandomRecipesUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            val result = getRecipesRandom()
            result.forEach {
                Log.d("Recipes", it.title)
            }
        }
    }

    suspend fun getRecipesRandom(): List<Recipe> {
        val limitLicense = true
        val tags = "vegetarian,dessert"
        val number = 10

        return getRandomRecipesUseCase.invoke(limitLicense, tags, number, API_KEY).recipes
    }
}