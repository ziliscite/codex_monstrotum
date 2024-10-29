package com.compose.dungeonsanddragons.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.dungeonsanddragons.R
import com.compose.dungeonsanddragons.util.Dimens

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.1f, targetValue = 0.8f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun CardShimmerEffect(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.monsterCardSize.first, Dimens.monsterCardSize.second)
                .clip(MaterialTheme.shapes.small)
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .width(Dimens.monsterCardSize.first)
                .height(250.dp)
                .clip(MaterialTheme.shapes.small)
                .padding(Dimens.extraSmallPadding * 2)
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(Dimens.monsterCardSize.first)
                .padding(Dimens.extraSmallPadding * 2)
                .clip(MaterialTheme.shapes.small)
                .height(37.dp)
                .shimmerEffect()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewShimmer() {
    CardShimmerEffect()
}
