package com.compose.dungeonsanddragons.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.MonsterList
import com.compose.dungeonsanddragons.presentation.home.components.HomeTopBar

@Composable
fun HomeScreen(
    monsters: LazyPagingItems<ResultsItem>,
    navigateToSearch: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .windowInsetsPadding(insets = WindowInsets.safeDrawing)
    ) {
        HomeTopBar(
            onSearchClick = navigateToSearch,
        ) { }

        Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))

        MonsterList(
            monsters = monsters,
            modifier = Modifier.fillMaxSize(),
            onClick = navigateToDetail
        )
    }
}
