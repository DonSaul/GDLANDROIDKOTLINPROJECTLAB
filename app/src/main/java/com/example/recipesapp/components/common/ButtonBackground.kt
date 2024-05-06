package com.example.recipesapp.components.common

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.DpOffset

data class ButtonBackground(
    @DrawableRes val icon: Int,
    val offset: DpOffset = DpOffset.Zero
)
