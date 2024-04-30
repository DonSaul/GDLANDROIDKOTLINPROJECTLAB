package com.example.recipesapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.usecase.GetRandomRecipesUseCase
import com.example.recipesapp.data.usecase.GetSearchRecipesUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getSearchRecipesUseCase: GetSearchRecipesUseCase
) : ViewModel() {

    private val _stateR = MutableStateFlow<State<RecipesArray>>(State.Loading)
    val stateR = _stateR as StateFlow<State<RecipesArray>>

    //val _recipesR = MutableStateFlow<List<Recipe>>(emptyList())
    //val recipeR = _recipesR as StateFlow<List<Recipe>>

    private val _state = MutableStateFlow<State<RecipeSearch>>(State.Loading)
    val state = _state as StateFlow<State<RecipeSearch>>

    init {
        viewModelScope.launch {
            getRecipesRandom()
            getSearchRecipes()
        }
    }

    fun getSearchRecipes(query: String = "", diet: String = "") {
        viewModelScope.launch {
            _state.value = State.Loading
            try {

                val result = getSearchRecipesUseCase.invoke(API_KEY,query,diet)
                _state.value = State.Success(result)

            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }
    fun getSearchRecipe2(query: String) {
        viewModelScope.launch {
            getSearchRecipes(query)

        }
    }

    suspend fun getRecipesRandom() {
        _stateR.tryEmit(State.Loading)
        try {
            val limitLicense = true
            val tags = ""
            val number = 10

            val result = getRandomRecipesUseCase.invoke(
                limitLicense,
                tags,
                number,
                "be0bdc6d7c5d4fa88cc8453fff432cb2"
            )
            _stateR.tryEmit(State.Success(result))
            //_recipeR.tryEmit(result.recipes as List<Recipe>)
        } catch (e: Exception) {
            _state.tryEmit(State.Error(e.message.toString()))
        }
    }

    /*fun searchRecipes(tags: String = "tacos") {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val limitLicense = true
                val number = 10

                val result = getRandomRecipesUseCase.invoke(
                    limitLicense,
                    tags,
                    number,
                    "be0bdc6d7c5d4fa88cc8453fff432cb2"
                )
                    _state.value = State.Success(result)
                _recipes.value = result.recipes
            } catch (e: Exception) {
                _state.value = State.Error(e.message ?: "Unknown error")
            }
        }
    }*/


}