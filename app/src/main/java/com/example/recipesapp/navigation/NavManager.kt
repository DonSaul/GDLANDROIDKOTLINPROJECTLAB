package com.example.recipesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 *  Composable for handling navigation between views.
 * */
@Composable
fun NavManager() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            //TO-DO
        }
        composable("Detail/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            //TO-DO
        }
    }
}