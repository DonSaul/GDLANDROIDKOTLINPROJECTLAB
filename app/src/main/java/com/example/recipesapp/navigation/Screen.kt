package com.example.recipesapp.navigation

sealed class Screen (val route: String, val title:  String) {
    object Home : Screen("home_screen", "Home")
    object Detail : Screen("detail_screen", "Detail")

    object Splash: Screen("splash_screen", "Splash")
}