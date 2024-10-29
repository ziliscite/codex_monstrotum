package com.compose.dungeonsanddragons.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.MonsterList
import com.compose.dungeonsanddragons.presentation.home.components.HomeBottomDrawer
import com.compose.dungeonsanddragons.presentation.home.components.HomeTopBar

@Composable
fun HomeScreen(
    monsters: LazyPagingItems<ResultsItem>,
    navigateToSearch: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
        .statusBarsPadding()
        .windowInsetsPadding(insets = WindowInsets.safeDrawing)
    ) {
        // Your main content here
        Column {
            HomeTopBar(
                onSearchClick = navigateToSearch,
            ) { isBottomSheetVisible = true }

            Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))

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
