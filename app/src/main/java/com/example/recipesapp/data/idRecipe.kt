package com.example.recipesapp.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.recipesapp.navigation.Screen

object IdRecipe{
    var idRecipe = 0
}


object lastScreen {
    var lastScreen = Screen.Home.route
}