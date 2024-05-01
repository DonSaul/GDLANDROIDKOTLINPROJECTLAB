import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.recipesapp.components.ListRecipes
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.viewModel.RecipeViewModel
import com.example.recipesapp.viewModel.State

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.components.RecipeCard
import com.example.recipesapp.model.Result

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    idSelected: Int,
    onIdSelectedChange: (Int) -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    val uiState2 = viewModel.stateR.collectAsState().value
    var searchText by remember { mutableStateOf("") }
    var selectedItems by remember { mutableStateOf(List(11) { false }) }

    Column(modifier = modifier.fillMaxSize()) {
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
                val dataRecomendations = (uiState2 as State.Success).data
                SearchBar(
                    searchString=searchText,
                    selectedItems = selectedItems,
                    placeholder = "Search recipes...",
                    action = { searchString, selectedItems -> viewModel.getSearchRecipes(searchString, selectedItems) }
                )
                Text(
                    text = "Recommendations",
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                RecommendationsComponent(dataRecomendations.recipes)
                Text(
                    text = "Recipes",
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )

                ReceiptsComponent(data.results, isLoading = false,
                    idSelected = idSelected,
                    onRecipeClick = { clickedRecipeId ->
                        onIdSelectedChange(clickedRecipeId)
                        navController.navigate(Screen.Detail.route)
                    })
            }
        }
    }
}


private fun selectedItemsToString(selectedItems: List<Boolean>): String {
    val selectedItemsStringList = mutableListOf<String>()
    val menuItems = listOf(
        "Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian",
        "Ovo-Vegetarian", "Vegan", "Pescarian", "Paleo", "Primal", "Low FODMAP", "Whole 30"
    )
    for ((index, isSelected) in selectedItems.withIndex()) {
        if (isSelected) {
            selectedItemsStringList.add(menuItems[index])
        }
    }

    return selectedItemsStringList.joinToString(", ")
}

@Composable
fun SearchBar(
    searchString: String,
    placeholder: String,
    selectedItems: List<Boolean>, // Agregar parámetro para los elementos seleccionados
    action: (String, String) -> Unit // Update the action parameter to accept a String parameter
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by remember { mutableStateOf("") }
    var menuExpanded by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val offsetWidth = screenWidth - 60.dp

    val menuItems = listOf("Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian",
        "Ovo-Vegetarian", "Vegan", "Pescarian", "Paleo", "Primal", "Low FODMAP", "Whole 30")

    val selectedItems = remember { mutableStateOf(List(menuItems.size) { false }) }

    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text(placeholder) },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    action(searchText, selectedItemsToString(selectedItems.value))
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )
                }
            },
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                action(searchText, selectedItemsToString(selectedItems.value))
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
            menuItems.forEachIndexed { index, item ->
                DropdownMenuItem( text = {  },
                    onClick = {
                        selectedItems.value = selectedItems.value.toMutableList().apply {
                            this[index] = !this[index]
                        }
                    },
                    modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)
                )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = selectedItems.value[index],
                            onCheckedChange = { isChecked ->
                                selectedItems.value = selectedItems.value.toMutableList().apply {
                                    this[index] = isChecked
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = item)

                }
            }
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
fun ReceiptsComponent(
    recipes: List<Result>,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    idSelected: Int,
    onRecipeClick: (Int) -> Unit // Click listener for recipe cards
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                MainAnimation(
                    modifier = Modifier
                        .width(250.dp)
                        .height(250.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .align(alignment = Alignment.Center), image = R.raw.re
                )
            }
        } else {
            LazyColumn() {
                itemsIndexed(recipes) { index, item ->
                    RecipeCardComponent(item, action = {
                        Log.i("debug", item.id.toString())
                        onRecipeClick(item.id)
                    })
                }
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
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .width(110.dp), fontSize = 13.sp, text = (recipe.title))
            //Text(text = (recipe.license))

        }
    }
}


@Composable
fun RecipeCardComponent(
    recipe: com.example.recipesapp.model.Result,
     action: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .clickable { action() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )
                Text(
                    text = recipe.imageType,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
*/
