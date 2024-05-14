import android.app.Application
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.recipesapp.ui.theme.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.components.common.BottomNavigation
import com.example.recipesapp.components.recipes.FavoriteList
import com.example.recipesapp.data.lastScreen
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onRecipeClick: (Int) -> Unit,
    navController: NavHostController
) {
    val context = LocalContext.current
    val viewModel = remember { FavoritesViewModel(context.applicationContext as Application) }
    val recipes by viewModel.recipes.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    lastScreen.lastScreen = Screen.Favorite.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorites") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF0E9E0)
                )
            )
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (isLoading) {
                Column(
                    modifier = Modifier
                        .background(MediumBrown)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MainAnimation(
                        modifier = Modifier
                            .width(400.dp)
                            .height(250.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .align(alignment = Alignment.CenterHorizontally),
                        image = R.raw.re
                    )
                }
            }
            else {
                Row(modifier = Modifier.fillMaxSize().background(LightBrown)) {
                    if(recipes.isEmpty()){
                        Column(modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(modifier = Modifier.height(35.dp))
                            Text(text = "Empty list", color = Color.LightGray, fontStyle = FontStyle.Italic)
                        }

                    }
                    FavoriteList(
                        recipes = recipes,
                        onRecipeClick = { recipeId ->
                            onRecipeClick(recipeId)
                        },
                        onDeleteFavorite = { recipeId ->
                            viewModel.deleteFavorite(recipeId)
                        }
                    )
                }
            }
        }
    }
}


