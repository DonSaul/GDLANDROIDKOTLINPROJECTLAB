package com.example.recipesapp.navigation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.ui.theme.LightBrown
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2500)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(LightBrown)
            .testTag(
                tag = "splash_screen"
            )
    ) {
        MainAnimation(
            modifier = Modifier
                .width(400.dp)
                .height(250.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.Center),
            image = R.raw.re
        )
    }

}