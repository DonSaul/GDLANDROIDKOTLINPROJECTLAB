package com.example.recipesapp.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipesapp.navigation.Screen
import androidx.compose.ui.graphics.Color
import com.example.recipesapp.ui.theme.LightBrown
import androidx.compose.material.icons.Icons
import com.example.recipesapp.components.common.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                modifier = Modifier.background(LightBrown),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF0E9E0)
                ))
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            colors = CardDefaults.cardColors(
                //containerColor = MaterialTheme.colorScheme.primaryContainer,
                containerColor = LightBrown
            ),
        ) {
        }
    }
}




