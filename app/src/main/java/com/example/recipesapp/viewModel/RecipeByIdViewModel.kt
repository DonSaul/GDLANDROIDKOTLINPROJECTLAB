package com.example.recipesapp.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.IdRecipe
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeByIdViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State<Recipe?>>(State.Loading)
    val state = _state as StateFlow<State<Recipe?>>

    init {
        viewModelScope.launch{
            getRecipeById(IdRecipe.idRecipe)
        }
    }

    fun getRecipeById(recipeId: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val result = recipeRepository.getRecipeById(recipeId, API_KEY)
                if (result.isSuccessful) {
                    _state.value = State.Success(result.body())
                } else {
                    _state.value = State.Error("Failed to fetch recipe")
                }
            } catch (e: Exception) {
                _state.value = State.Error(e.message ?: "Unknown error")
            }
        }
    }
}
