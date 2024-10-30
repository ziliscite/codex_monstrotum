package com.compose.dungeonsanddragons.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.CodexSearchbar
import com.compose.dungeonsanddragons.presentation.common.MonsterList

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    navigate: (String) -> Unit,
    event: (SearchEvent) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
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

        Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))

        state.monsters?.let {
            val monsters = it.collectAsLazyPagingItems()
            MonsterList(
                monsters = monsters,
                onClick = navigate,
                isSearch = true
            )
        }
    }
}
