package com.example.recipesapp.viewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.recipesapp.data.IdRecipe
import com.example.recipesapp.data.local.RecipesDB
import com.example.recipesapp.data.local.entities.SeenRecipeEntity
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.data.usecase.GetRecipesInformationBulkUseCase
import com.example.recipesapp.data.usecase.GetSimilarRecipesUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeByIdViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val getSimilarRecipesUseCase: GetSimilarRecipesUseCase,
    private val getRecipesInformationBulkUseCase: GetRecipesInformationBulkUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<State<Recipe?>>(State.Loading)
    val state = _state as StateFlow<State<Recipe?>>


    private val _similarRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val similarRecipes = _similarRecipes.asStateFlow()

    private val db = Room.databaseBuilder(
        application,
        RecipesDB::class.java,
        "recipe_database"
    ).build()

    private val seenDao = db.seenDao()

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
                    getSimilarRecipes(recipeId) // Llamada a getSimilarRecipes después de obtener la receta actual
                } else {
                    _state.value = State.Error("Failed to fetch recipe")
                }
            } catch (e: Exception) {
                _state.value = State.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addSeen(recipeId: Int){
        viewModelScope.launch {
            val seenEntity = SeenRecipeEntity(recipeId)
            seenDao.insertSeen(seenEntity)
            println( seenDao.getSeenRecipes().toList().forEach{ println("Hola" + it) })

        }
    }

    fun getSimilarRecipes(recipeId: Int) {
        viewModelScope.launch {
            try {
                val similarRecipes = getSimilarRecipesUseCase.invoke(recipeId, API_KEY)
                val similarRecipeIds = similarRecipes.map { it.id.toLong() }
                val detailedRecipes = getRecipesInformationBulkUseCase.invoke(similarRecipeIds, API_KEY)
                _similarRecipes.value = detailedRecipes
            } catch (e: Exception) {
                // Manejo de errores
            }
        }
    }
}
