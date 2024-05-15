package com.example.recipesapp.navigation

import FavoritesScreen
import HomeScreen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipesapp.navigation.screens.DetailViewScreen
import com.example.recipesapp.navigation.screens.LoadingScreen
import com.example.recipesapp.navigation.screens.SettingScreen
import com.example.recipesapp.navigation.screens.seenHistoryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    var mContext = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        var idSelected by remember { mutableStateOf(0) }
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            composable(Screen.Splash.route) {
                LoadingScreen(navController = navController)
            }
            composable(route = Screen.Home.route) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    idSelected = idSelected,
                    navController = navController
                ) { newIdSelected ->
                    idSelected = newIdSelected
                    navController.popBackStack()
                    navController.navigate(Screen.Detail.route)
                }
            }
            composable(route = Screen.Detail.route) {
                DetailViewScreen(navController = navController, id = idSelected)
            }
            composable(
                route = Screen.DetailWithId.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("id") ?: 0
                DetailViewScreen(navController = navController, id = recipeId)
            }
            composable(route = Screen.Favorite.route) {
                FavoritesScreen(
                    onRecipeClick = { recipeId ->
                        // Navegar a la pantalla de detalles de la receta con el ID especificado
                        idSelected = recipeId
                        navController.navigate(Screen.Detail.route)
                    },
                    navController = navController
                )
            }
            composable(route = Screen.History.route) {
                seenHistoryScreen(
                    onRecipeClick = {recipeId ->
                        idSelected = recipeId
                        navController.navigate(Screen.Detail.route)
                    },
                    navController = navController)
            }
        }
    }
}