import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.data.impl.FavoritesRepositoryImpl
import com.example.recipesapp.data.impl.RecipesInformationBulkImpl
import com.example.recipesapp.data.impl.SearchHistoryImpl
import com.example.recipesapp.data.local.RecipesDB
import com.example.recipesapp.data.local.entities.FavoriteEntity
import com.example.recipesapp.data.local.entities.SearchHistoryEntity
import com.example.recipesapp.data.repository.FavoritesRepository
import com.example.recipesapp.data.repository.SearchHistoryRepository
import com.example.recipesapp.data.usecase.GetRecipesInformationBulkUseCase
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.utils.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        RecipesDB::class.java,
        "recipe_database"
    ).fallbackToDestructiveMigration()
        .build()

    private val searchDao = db.searchHistoryDao()
    private val searchHistory: SearchHistoryRepository = SearchHistoryImpl(searchDao)


    private val _history = MutableStateFlow<List<SearchHistoryEntity>>(emptyList())
    val history = _history.asStateFlow()

    init {
        viewModelScope.launch {

            getSearchHistory()
        }
    }

    private fun getSearchHistory() {
        viewModelScope.launch {
            searchHistory.getSearchHistory().collect { searchHistory ->
                _history.value = searchHistory
            }
        }
    }


    fun addHistorySearchRecord(searchHistoryEntity: SearchHistoryEntity){
        viewModelScope.launch {
            searchHistory.addSearch(searchHistoryEntity)
            getSearchHistory()
        }
    }
}