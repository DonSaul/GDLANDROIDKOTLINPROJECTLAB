package com.example.recipesapp.components.recipes

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.Result
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeItemFavorite(
    recipe: Recipe,
    action: () -> Unit,
    onDeleteFavorite: (Recipe) -> Unit
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(recipe)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                onDeleteFavorite(currentItem)
                // show = false
                return@rememberDismissState true
            } else false
        },
        positionalThreshold = { 0f }
    )
    AnimatedVisibility(
        visible = show,
        exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                DismissBackgroundFavorite(dismissState, isDelete = true)
            },
            dismissContent = {
                RecipeCardFavorite(recipe, action)
            }
        )
    }
    LaunchedEffect(show) {
        if (!show) {
            delay(800)
            onDeleteFavorite(currentItem)
        }
    }
}