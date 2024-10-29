package com.compose.dungeonsanddragons.presentation.home

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
import androidx.paging.compose.LazyPagingItems
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.CodexSearchbar
import com.compose.dungeonsanddragons.presentation.home.components.MonsterList

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
        // TODO(change this to a clickable icon)
        CodexSearchbar(
            modifier = Modifier.padding(Dimens.extraSmallPadding * 2),
            text = "",
            isReadOnly = true,
            onClick = {
                navigateToSearch()
            }
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = Dimens.mediumPaddingOne),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))

        MonsterList(
            monsters = monsters,
            modifier = Modifier.fillMaxSize(),
            onClick = navigateToDetail
        )
    }
}
