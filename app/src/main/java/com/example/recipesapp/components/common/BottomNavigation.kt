package com.example.recipesapp.components.common

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipesapp.R
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.ui.theme.LightBrown
import com.example.recipesapp.ui.theme.LightGray
import com.example.recipesapp.ui.theme.MediumBrown
import com.example.recipesapp.ui.theme.RoyalGray
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Teleport
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.items.wigglebutton.WiggleButton


val wiggleButtonItems = listOf(
    WiggleButtonItem(
        icon = R.drawable.outline_home,
        backgroundIcon = R.drawable.home,
        isSelected = false,
        description = R.string.Home,
        route = Screen.Home.route,
    ),
    WiggleButtonItem(
        icon = R.drawable.outline_favorite,
        backgroundIcon = R.drawable.favorite,
        isSelected = false,
        description = R.string.Heart,
        // TODO: Change with the Favorite Screen - Screen.Favorite.route
        route = Screen.Favorite.route,

    ),
    WiggleButtonItem(
        icon = R.drawable.outline_circle,
        backgroundIcon = R.drawable.circle,
        isSelected = false,
        description = R.string.Circle,
        // TODO: Change with the Favorite Screen - Screen.Settings.route
        route = Screen.Settings.route

    ),
)

const val Duration = 500
const val DoubleDuration = 1000

@Composable
fun BottomNavigation(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var selectedItem by remember(currentRoute) {
        mutableIntStateOf(
            when (currentRoute) {
                Screen.Home.route -> 0

                // TODO: Change the "Favorite" and "Settings" with the corresponded routes
                Screen.Favorite.route -> 1
                Screen.Settings.route -> 2
                else -> 0
            }
        )
    }
    AnimatedNavigationBar(
        modifier = Modifier
            .height(85.dp)
            .background(LightBrown), // (MaterialTheme.colorScheme.errorContainer),
        selectedIndex = selectedItem,
        ballColor = Color.White,
        ballAnimation = Teleport(tween(Duration, easing = LinearEasing)),
        indentAnimation = Height(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(
                DoubleDuration,
                easing = { OvershootInterpolator().getInterpolation(it) })
        )
    ) {
        wiggleButtonItems.forEachIndexed { index, it ->
            WiggleButton(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MediumBrown), // Opción para hacer los botones también transparentes
                isSelected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(it.route)
                },
                icon = it.icon,
                backgroundIcon = it.backgroundIcon,
                wiggleColor = LightGray,
                outlineColor = RoyalGray,
                contentDescription = stringResource(id = it.description),
                enterExitAnimationSpec = tween(
                    durationMillis = Duration,
                    easing = LinearEasing
                ),
                wiggleAnimationSpec = spring(dampingRatio = .45f, stiffness = 35f),
            )
        }
    }
}


