package com.example.recipesapp.components.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable
import com.example.recipesapp.R

@Stable
data class WiggleButtonItem(
    @DrawableRes val backgroundIcon: Int,
    @DrawableRes val icon: Int,
    var isSelected: Boolean,
    @StringRes val description: Int,
    val animationType: BellColorButton = BellColorButton(
        tween(500),
        background = ButtonBackground(R.drawable.plus)
    ),
     val route: String
)