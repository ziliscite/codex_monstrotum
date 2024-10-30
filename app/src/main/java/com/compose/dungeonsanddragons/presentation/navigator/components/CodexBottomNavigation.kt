package com.compose.dungeonsanddragons.presentation.navigator.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.dungeonsanddragons.R
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme
import com.compose.dungeonsanddragons.util.Dimens

@Composable
fun CodexBottomNavigation(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    visibility: Float = 1f,
    backgroundColor: Color = Color.Transparent
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.extraSmallPadding)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(Dimens.monsterCardSize.first / 2))
                .align(Alignment.BottomCenter)
                .background(
                    color = backgroundColor
                )
                .padding(horizontal = 16.dp)
                .alpha(visibility),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = selectedItem == 0,
                onClick = { onItemSelected(0) },
                icon = {
                    Column(
                        modifier = Modifier.padding(Dimens.extraSmallPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val iconId = if (selectedItem == 0) R.drawable.baseline_home_24 else R.drawable.ic_home
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = "Home"
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )

            // Spacer for the floating button
            Spacer(modifier = Modifier.weight(1f))

            NavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = selectedItem == 2,
                onClick = {
                    onItemSelected(2)
                },
                icon = {
                    Column(
                        modifier = Modifier.padding(Dimens.extraSmallPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val iconId = if (selectedItem == 2) R.drawable.baseline_bookmark_24 else R.drawable.ic_bookmark
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = "Favorites"
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }

        // Floating search button
        FloatingActionButton(
            onClick = {
                onItemSelected(1)
            },
            modifier = Modifier
                .offset(y = (-30).dp)
                .size(70.dp)
                .padding(Dimens.extraSmallPadding)
                .clip(RoundedCornerShape(Dimens.monsterCardSize.first / 2))
                .align(Alignment.TopCenter)
                .alpha(visibility),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CodexBottomNavigationPreview() {
    DungeonsAndDragonsTheme {
        CodexBottomNavigation(
            selectedItem = 2,
            onItemSelected = {}
        )
    }
}
