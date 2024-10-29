package com.compose.dungeonsanddragons.presentation.detail

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.util.MonsterResult

data class DetailState(
    val index: String = "",
    val monster: MonsterResult<Monster>,
    val favoriteMonster: MonsterEntity? = null
)
