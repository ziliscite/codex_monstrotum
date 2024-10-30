package com.compose.dungeonsanddragons.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.presentation.common.MonsterList
import com.compose.dungeonsanddragons.presentation.home.components.HomeBottomDrawer
import com.compose.dungeonsanddragons.presentation.home.components.HomeTopBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    monsters: LazyPagingItems<ResultsItem>,
    navigateToSearch: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            HomeTopBar(
                onSearchClick = navigateToSearch,
            ) { isBottomSheetVisible = true }

            MonsterList(
                monsters = monsters,
                modifier = Modifier.fillMaxSize(),
                onClick = navigateToDetail
            )
        }

        if (isBottomSheetVisible) {
            HomeBottomDrawer(
                onDismiss = { isBottomSheetVisible = false }
            )
        }
    }
}
