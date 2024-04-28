package com.example.recipesapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.usecase.GetRandomRecipesUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State<RecipesArray>>(State.Loading)
    val state = _state as StateFlow<State<RecipesArray>>


    val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipe = _recipes as StateFlow<List<Recipe>>

    init {
        viewModelScope.launch {
            getRecipesRandom()
        }
    }

    private suspend fun getRecipesRandom() {
        _state.tryEmit(State.Loading)
        try {
            val limitLicense = true
            val tags = "vegetarian,dessert"
            val number = 10

            val result = getRandomRecipesUseCase.invoke(
                limitLicense,
                tags,
                number,
                "a2c4ac16a2af488987cc1960999946eb"
            )
            _state.tryEmit(State.Success(result))
            _recipes.tryEmit(result.recipes)
        } catch (e: Exception) {
            _state.tryEmit(State.Error(e.message.toString()))
        }
    }


    private suspend fun getDiff(tags: String) {
        _state.tryEmit(State.Loading)
        try {
            val limitLicense = true
            val number = 10

            val result = getRandomRecipesUseCase.invoke(
                limitLicense,
                tags,
                number,
                "a2c4ac16a2af488987cc1960999946eb"
            )
            _state.tryEmit(State.Success(result))
            _recipes.tryEmit(result.recipes)
        } catch (e: Exception) {
            _state.tryEmit(State.Error(e.message.toString()))
        }
    }

    fun changeRandom() {
        viewModelScope.launch {
            getDiff("vegan")
        }
    }



    fun execNaco(){
        println("e")
    }
}