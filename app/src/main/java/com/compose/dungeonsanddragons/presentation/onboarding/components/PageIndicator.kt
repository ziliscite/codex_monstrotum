package com.compose.dungeonsanddragons.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.compose.dungeonsanddragons.presentation.Dimens
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize) {
            Box(
                modifier = Modifier
                    .size(Dimens.indicatorSize)
                    .clip(CircleShape)
                    .background(color = if (it == selectedPage) selectedColor else unSelectedColor)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPageIndicator() {
    DungeonsAndDragonsTheme {
        PageIndicator(pageSize = 4, selectedPage = 1)
    }
}
