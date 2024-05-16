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
import com.example.recipesapp.model.Result
import kotlinx.coroutines.delay

/**
 * Composable representing a recipe item with swipe-to-dismiss functionality that only works from right to left.
 *
 * @param recipe The recipe to display.
 * @param action Callback invoked when the recipe item is clicked.
 * @param onAddFavorite Callback invoked when the recipe item is dismissed.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeItem(
    recipe: Result,
    action: () -> Unit,
    onAddFavorite: (Result) -> Unit
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(recipe)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                onAddFavorite(currentItem)
                show = false
                Toast.makeText(context, "Added to favorites!", Toast.LENGTH_SHORT).show()
                return@rememberDismissState true
            } else false
        }, positionalThreshold = { 0f }

    )
    AnimatedVisibility(
        visible = show, exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                DismissBackground(dismissState)
            },

            dismissContent = {
                RecipeCard(recipe, action)
            }
        )
    }

    LaunchedEffect(show) {
        if (!show) {
            delay(800)
            onAddFavorite(currentItem)
        }
    }
}
