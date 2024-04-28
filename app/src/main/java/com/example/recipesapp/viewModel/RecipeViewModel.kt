package com.example.recipesapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.data.usecase.GetRandomRecipesUseCase
import com.example.recipesapp.data.usecase.GetSearchRecipesUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.model.RecipeSearchList
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getSearchRecipesUseCase: GetSearchRecipesUseCase
) : ViewModel() {

    //private val _state = MutableStateFlow<State<RecipesArray>>(State.Loading)
    //val state = _state as StateFlow<State<RecipesArray>>

    private val _state = MutableStateFlow<State<RecipeSearch>>(State.Loading)
    val state = _state as StateFlow<State<RecipeSearch>>

    private var job: Job? = null

    init {
        viewModelScope.launch {
           //getRecipesRandom()
            getSearchRecipes()
        }
    }

    /*suspend fun getRecipesRandom() {
        job?.cancel()
        job = viewModelScope.launch {
        _state.tryEmit(State.Loading)
        try {
            val limitLicense = true
            val tags = "vegetarian,dessert"
            val number = 10

            val result = getRandomRecipesUseCase.invoke(limitLicense, tags, number, API_KEY)
            _state.tryEmit(State.Success(result))
        } catch (e: Exception) {
            _state.tryEmit(State.Error(e.message.toString()))
        }
        }
    }*/
     fun getSearchRecipes(query: String = "Taco") {
        viewModelScope.launch {
            _state.value = State.Loading
            try {

                val result = getSearchRecipesUseCase.invoke(API_KEY,query)
                _state.value = State.Success(result)

            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }


     /*fun getRecipe(query: String? = "Taco"){
        job?.cancel()
        job = viewModelScope.launch {
            _state.tryEmit(State.Loading)
            try {
                val limitLicense = true
                val tags = "vegetarian,dessert"
                val number = 10
                //val service = RetrofitServiceFactory.makeRetrofitService()
                val list = null
                    //service.listRecipes("complexSearch", "178d63d1ecc749af92d4180120d05054", query)
                    //service.listRecipes("complexSearch", API_KEY, query)
                val result = getRandomRecipesUseCase.invoke(limitLicense, tags, number, API_KEY)
                _state.tryEmit(State.Success(result))
                _state1.tryEmit(State.Success(list))
            } catch (e: Exception){
                _state1.tryEmit(State.Error(e.message.toString()))
            }

        }
        job?.cancel()
    }*/

    fun getSearchRecipe2(query: String) {
        viewModelScope.launch {
            getSearchRecipes(query)

        }
    }

    /*fun updateRecipeSearch(list: RecipeSearch){
        job?.cancel()
        job = viewModelScope.launch {
            try {
                _state1.tryEmit(State.Success(list))
            } catch (e: Exception){
                _state1.tryEmit(State.Error(e.message.toString()))
            }

        }
        job?.cancel()
    }*/


}