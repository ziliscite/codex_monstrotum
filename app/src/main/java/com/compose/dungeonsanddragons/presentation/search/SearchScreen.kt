package com.compose.dungeonsanddragons.presentation.search

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.dungeonsanddragons.presentation.Dimens
import com.compose.dungeonsanddragons.presentation.common.CodexSearchbar
import com.compose.dungeonsanddragons.presentation.home.components.MonsterList
import com.compose.dungeonsanddragons.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    navigate: (String) -> Unit,
    event: (SearchEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .windowInsetsPadding(insets = WindowInsets.safeDrawing)
    ) {
        CodexSearchbar(
            modifier = Modifier.padding(Dimens.extraSmallPadding * 2),
            text = state.searchQuery,
            isReadOnly = false,
            onQueryChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {
                event(SearchEvent.SearchMonsters)
            }
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = Dimens.smallPaddingOne),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))

        state.monsters?.let {
            val monsters = it.collectAsLazyPagingItems()
            MonsterList(
                monsters = monsters,
            ) {
                navigate(Route.DetailsScreen.route)
            }
        }
    }
}
