package com.example.recipesapp.navigation.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipesapp.R
import com.example.recipesapp.assets.MainAnimation
import com.example.recipesapp.components.common.BottomNavigation
import com.example.recipesapp.components.recipes.SeenHistoryList
import com.example.recipesapp.data.lastScreen
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.ui.theme.LightBrown
import com.example.recipesapp.ui.theme.MediumBrown
import com.example.recipesapp.viewModel.HistoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun seenHistoryScreen(
    onRecipeClick: (Int) -> Unit,
    navController: NavHostController
){
    val context = LocalContext.current
    val viewModel = remember {HistoryViewModel(context.applicationContext as Application)}
    val recipes by viewModel.recipes.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    var menuExpanded by remember { mutableStateOf(false) }
    lastScreen.lastScreen = Screen.History.route

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val offsetWidth = screenWidth - 60.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "History") },

                modifier = Modifier.background(LightBrown),
                /*navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.History.route)}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },*/
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF0E9E0)
                ),
                actions = {IconButton(
                    onClick = { menuExpanded = !menuExpanded }
                ) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
                }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        offset = DpOffset(
                            x = offsetWidth,
                            y = 20.dp
                        )
                    ) {
                        Text(text = "Clear history", modifier = Modifier.clickable { viewModel.deleteAllHistory() })
                    }
                          }
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
                    SeenHistoryList(
                        recipes = recipes,
                        onRecipeClick = { recipeId ->
                            onRecipeClick(recipeId)
                        }
                    )
                }
            }
        }
    }

}