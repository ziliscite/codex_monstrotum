package com.compose.dungeonsanddragons.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.compose.LazyPagingItems
import androidx.paging.LoadState
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme

@Composable
fun MonsterList(
    modifier: Modifier = Modifier,
    monsters: List<MonsterEntity>,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (monsters.isEmpty()) {
            EmptyScreen(errorString = "You haven't saved any monster so far!")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .padding(Dimens.extraSmallPadding)
                    .fillMaxSize()
            ) {
                items(count = monsters.size) {
                    monsters[it].let { monster ->
                        MonsterCard(
                            monster = monster,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.extraSmallPadding),
                            onClick = {
                                onClick(monster.index)
                            }
                        )
                    }
                }

                if (monsters.size % 2 == 0) {
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                } else {
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MonsterList(
    modifier: Modifier = Modifier,
    monsters: LazyPagingItems<ResultsItem>,
    onClick: (String) -> Unit,
    isSearch: Boolean = false
) {
    val (isReady, isError) = handlePagingResult(monsters = monsters)
    val isFirstLoadComplete = remember {
        mutableStateOf(false)
    }

    if (isReady && !isFirstLoadComplete.value) {
        isFirstLoadComplete.value = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isReady) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .padding(Dimens.extraSmallPadding)
                    .fillMaxSize()
            ) {
                items(count = monsters.itemCount) {
                    monsters[it]?.let { monster ->
                        MonsterCard(
                            monster = monster,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.extraSmallPadding),
                            onClick = {
                                onClick(monster.index)
                            }
                        )
                    }
                }
                if (monsters.itemCount % 2 == 0) {
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                } else {
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        } else if(isError != null) {
            EmptyScreen(isError)
        }
    }
}

@Composable
fun MonsterList(
    modifier: Modifier = Modifier,
    monsters: LazyPagingItems<ResultsItem>,
    onClick: (String) -> Unit
) {
    val (isReady, isError) = handlePagingResult(monsters = monsters)
    val isFirstLoadComplete = remember {
        mutableStateOf(false)
    }

    if (isReady && !isFirstLoadComplete.value) {
        isFirstLoadComplete.value = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isReady || (isFirstLoadComplete.value && isError != null)) {
            if (isError != null) {
                CodexSnackbar(
                    modifier = Modifier
                        .padding(Dimens.smallPaddingOne)
                        .align(Alignment.TopCenter)
                        .zIndex(5f),
                    message = isError.error.message.toString()
                ) {
                    monsters.refresh()
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .padding(Dimens.extraSmallPadding)
                    .fillMaxSize()
            ) {
                items(count = monsters.itemCount) {
                    monsters[it]?.let { monster ->
                        MonsterCard(
                            monster = monster,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.extraSmallPadding),
                            onClick = {
                                onClick(monster.index)
                            }
                        )
                    }
                }
                if (monsters.itemCount % 2 == 0) {
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                } else {
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        } else if(isError != null) {
            EmptyScreen(isError) {
                monsters.refresh()
            }
        }
    }
}

@Composable
fun handlePagingResult(
    monsters: LazyPagingItems<ResultsItem>
) : Pair<Boolean, LoadState.Error?> {
    val loadState = monsters.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            Pair(false, null)
        }
        error != null -> {
            Pair(false, error)
        }
        else -> Pair(true, null)
    }
}

@Composable
private fun ShimmerEffect() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns
        modifier = Modifier.padding(Dimens.extraSmallPadding)
    ) {
        items(12) {
            CardShimmerEffect(
                modifier = Modifier.padding(Dimens.extraSmallPadding)
            )
        }
    }
}

@Composable
@Preview
fun MonsterShimmerPreview() {
    DungeonsAndDragonsTheme {
        ShimmerEffect()
    }
}
