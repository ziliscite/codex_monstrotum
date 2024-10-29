package com.compose.dungeonsanddragons.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.dungeonsanddragons.presentation.common.MonsterList
import com.compose.dungeonsanddragons.presentation.navgraph.Route

@Composable
fun FavoriteScreen(
    state: FavoriteState,
    navigate: (String) -> Unit
) {
    Column {
        MonsterList(
            modifier = Modifier.fillMaxSize(),
            monsters = state.monsters,
        ) {
            navigate(Route.DetailsScreen.route)
        }
    }
}
