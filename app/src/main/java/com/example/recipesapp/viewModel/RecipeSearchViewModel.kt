package com.example.recipesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeSearchViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<State<RecipeSearch>>(State.Loading)
    val state = _state as StateFlow<State<RecipeSearch>>

    private var job: Job? = null

    /*suspend fun getRecipe2(query: String? = "Taco"): RecipeSearch{
        val service = RetrofitServiceFactory.makeRetrofitService()
        val list =
            service.listRecipes("complexSearch", API_KEY, query)
        return list
    }*/
}