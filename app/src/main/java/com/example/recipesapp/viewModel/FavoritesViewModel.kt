import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.data.impl.FavoritesRepositoryImpl
import com.example.recipesapp.data.impl.RecipesInformationBulkImpl
import com.example.recipesapp.data.local.RecipesDB
import com.example.recipesapp.data.local.entities.FavoriteEntity
import com.example.recipesapp.data.repository.FavoritesRepository
import com.example.recipesapp.data.usecase.GetRecipesInformationBulkUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.utils.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        RecipesDB::class.java,
        "recipe_database"
    ).build()

    private val favoriteDao = db.favoriteDao()
    private val favoritesRepository: FavoritesRepository = FavoritesRepositoryImpl(favoriteDao)

    private val api = RetrofitServiceFactory.makeRetrofitService()
    private val recipesInformationBulkImpl = RecipesInformationBulkImpl(api)
    private val getRecipesInformationBulkUseCase = GetRecipesInformationBulkUseCase(recipesInformationBulkImpl)

    private val _favorites = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favorites = _favorites.asStateFlow()

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    init {
        viewModelScope.launch {
            getAllFavorites()
        }
    }



    fun deleteFavorite(recipeId: Int) {
        viewModelScope.launch {
            favoritesRepository.deleteFavorite(recipeId)
        }
    }

    private fun getAllFavorites() {
        viewModelScope.launch {
            favoritesRepository.getAllFavorites().collect { favoriteList ->
                _favorites.value = favoriteList
                Log.d("FavoritesViewModel", "Favorite IDs: ${favoriteList.map { it.recipeId }}")
                fetchRecipesInformation(favoriteList.map { it.recipeId.toLong() })
            }
        }
    }

    private fun fetchRecipesInformation(ids: List<Long>) {
        viewModelScope.launch {
            try {
                Log.d("FavoritesViewModel", "Fetching recipe information for IDs: $ids")
                _isLoading.value = true // Establecer el estado de carga en true antes de la llamada a la API
                val recipes = getRecipesInformationBulkUseCase.invoke(ids, API_KEY)
                Log.d("FavoritesViewModel", "Recipes response: $recipes")
                _recipes.value = recipes
            } catch (e: Exception) {
                Log.e("FavoritesViewModel", "Error fetching recipe information", e)
                // Manejo de errores
            } finally {
                _isLoading.value = false // Establecer el estado de carga en false después de la llamada a la API
            }
        }
    }
}