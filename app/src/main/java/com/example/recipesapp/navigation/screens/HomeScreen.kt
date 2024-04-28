import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.components.ListRecipes
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.RecipeSearch
import com.example.recipesapp.model.RecipesArray
import com.example.recipesapp.viewModel.RecipeSearchViewModel
import com.example.recipesapp.viewModel.RecipeViewModel
import com.example.recipesapp.viewModel.State
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen() {

    val viewModel: RecipeViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    //val uiState2 = viewModel.state1.collectAsState()
    //val viewModel2: RecipeSearchViewModel = hiltViewModel()

    //val recipelist = viewModel.state1.collectAsState().value
    //var list2: RecipeSearch


    ///println("nuevo "+list2.results)
    //viewModel.viewModelScope.launch{viewModel2.getRecipe("Fish")}

    val searchText = remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState.value) {
            is State.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Text(text = "Loading")
                }
            }

            is State.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error")
                    Text(text = (uiState.value as State.Error).error)
                }
            }

            is State.Success -> {
                val data = (uiState.value as State.Success).data
                //println(data.results)
                //println(data.results)
                //viewModel.getSearchRecipes("fish")
                //val data2 = (uiState2.value as State.Success).data
                //val data2 = (recipelist.value as State.Success).data
                //println(data2.totalResults)
                println("hola")
                //println(uiState2.toString())
                println("hola")


                //runBlocking {list2 = getRecipe2("fish")}
                //viewModel.getRecipe("Fish")
                //viewModel.updateRecipeSearch(list2)
                //runBlocking {viewModel.getRecipe("taco")}
                //val data2 = (recipelist.value as State.Success).data
                //val receta = viewModel.getSearchRecipe("fish")
                //println(list2.results)


                Column(modifier = Modifier.fillMaxSize()) {
                    SearchBar(
                        placeholder = "Search recipes...",
                        action = {query -> viewModel.getSearchRecipe2(query)}
                    )
                    Text(
                        text = "Recomendaciones",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )
                    //RecommendationsComponent(data.recipes)
                    Text(
                        text = "Recetas", modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )
                    //ReceiptsComponent(data.recipes)
                    ListRecipes(recipes = data.results, isLoading = false)
                }
            }
        }
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    placeholder: String,
    action: (String) -> Unit // Update the action parameter to accept a String parameter
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by remember { mutableStateOf("") }
    var menuExpanded by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val offsetWidth = screenWidth - 60.dp

    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text(placeholder) },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    action(searchText) // Pass the search text to the action callback
                    Log.i("debug", "hola mundo")
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )
                }
            },
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                action(searchText) // Pass the search text to the action callback
            }),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = { menuExpanded = !menuExpanded },
            modifier = Modifier.weight(0.2f)
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            offset = DpOffset(
                x = offsetWidth,
                y = 20.dp
            )
        ) {
            DropdownMenuItem(onClick = {
                menuExpanded = false
            },
                text = { Text(text = "vaors") }
            )
        }
    }
}


@Composable
fun RecommendationsComponent(recipes: List<Recipe>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow {
            items(recipes) {
                RecommendedReceipt(it)
            }
        }

    }
}

@Composable
fun ReceiptsComponent(recipes: List<Recipe>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        LazyColumn {
            items(recipes) {
                RecipeCardComponent(recipeName = it.title, description = it.sourceName)
            }
        }
    }
}

@Composable
fun RecommendedReceipt(recipe: Recipe) {
    Column {
        Card(
            modifier = Modifier
                .size(
                    width = 160.dp,
                    height = 160.dp
                )
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp, 0.dp),
        ) {
            Text(text = (recipe.title))
            Text(text = (recipe.license))

        }
    }
}


@Composable
fun RecipeCardComponent(
    recipeName: String = "Recipe Name",
    description: String = "Description",
    category: String = "Category",
    onCategoryClicked: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .background(Color.Cyan),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipeName,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

val items = listOf(
    "",
    "",
    "",
    "",
    "",
)


val receiptItems = listOf(
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
