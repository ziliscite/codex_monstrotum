package com.compose.dungeonsanddragons.presentation.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.dungeonsanddragons.presentation.Dimens
import com.compose.dungeonsanddragons.presentation.common.shimmerEffect
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme

@Composable
fun DetailShimmerEffect(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .windowInsetsPadding(insets = WindowInsets.safeDrawing)
            .padding(
                Dimens.smallPaddingOne,
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.large)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(30.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .height(30.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.height(Dimens.mediumPaddingOne))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(Dimens.mediumPaddingOne))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .height(20.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.height(Dimens.mediumPaddingOne))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(Dimens.mediumPaddingOne))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
    }
}

@Preview
@Composable
private fun PreviewDetailShimmerEffect() {
    DungeonsAndDragonsTheme {
        DetailShimmerEffect()
    }
}

