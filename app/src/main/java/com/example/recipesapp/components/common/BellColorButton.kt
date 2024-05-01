package com.example.recipesapp.components.common

import ButtonBackground
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import com.exyte.animatednavbar.utils.rotationWithTopCenterAnchor
import kotlin.math.PI
import kotlin.math.sin

data class ButtonBackground(
    @DrawableRes val icon: Int,
    val offset: DpOffset = DpOffset.Zero
)

@Stable
abstract class ColorButtonAnimation(
    open val animationSpec: FiniteAnimationSpec<Float> = tween(10000),
    open val background: ButtonBackground,
) {
    @Composable
    abstract fun AnimatingIcon(
        modifier: Modifier,
        isSelected: Boolean,
        isFromLeft: Boolean,
        icon: Int,
    )
}
class BellColorButton(
    override val animationSpec: FiniteAnimationSpec<Float> = tween(),
    override val background: ButtonBackground,
    private val maxDegrees: Float = 30f,
) : ColorButtonAnimation(animationSpec, background) {

    @Composable
    override fun AnimatingIcon(
        modifier: Modifier,
        isSelected: Boolean,
        isFromLeft: Boolean,
        icon: Int,
    ) {
        val rotationFraction = animateFloatAsState(
            targetValue = if (isSelected) 1f else 0f,
            animationSpec = animationSpec,
            label = "rotationFractionAnimation"
        )

        val color = animateColorAsState(
            targetValue = if (isSelected) Color.Black else LightGray,
            label = "colorAnimation"
        )

        Icon(
            modifier = modifier
                .rotationWithTopCenterAnchor(
                    if (isSelected) degreesRotationInterpolation(
                        maxDegrees,
                        rotationFraction.value
                    ) else 0f
                ),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color.value
        )
    }

    private fun degreesRotationInterpolation(maxDegrees: Float, fraction: Float) =
        sin(fraction * 2 * PI).toFloat() * maxDegrees
}