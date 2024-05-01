package com.example.recipesapp.navigation

import HomeScreen
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipesapp.navigation.screens.DetailViewScreen
import com.example.recipesapp.navigation.screens.LoadingScreen

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
        }
    }
}