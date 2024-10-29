package com.compose.dungeonsanddragons.presentation.favorite

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity

data class FavoriteState(
    val monsters: List<MonsterEntity> = emptyList()
)