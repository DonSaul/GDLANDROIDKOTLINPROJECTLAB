package com.example.recipesapp.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen")

    object Splash: Screen("splash_screen")
}