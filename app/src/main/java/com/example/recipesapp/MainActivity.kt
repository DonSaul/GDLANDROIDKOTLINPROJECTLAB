package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.recipesapp.data.RetrofitServiceFactory
import com.example.recipesapp.ui.theme.RecipesAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        service = Call API service

        Params String
        type: Type of search in API (complexSearch, findByNutrients, findByIngredients etc.)
        apiKey: 178d63d1ecc749af92d4180120d05054 (only 150 call a day... Replace with new account if more needed)
        query: Type of search ex. "Tacos" (only works with complexSearch)
         */

        val service = RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            val recipes = service.listRecipes("complexSearch",
                "178d63d1ecc749af92d4180120d05054",
                "Taco")
            println(recipes)
        }

        setContent {
            RecipesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipesAppTheme {
        Greeting("Android")
    }
}