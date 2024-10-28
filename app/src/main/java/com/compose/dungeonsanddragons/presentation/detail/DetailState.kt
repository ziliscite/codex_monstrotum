package com.compose.dungeonsanddragons.presentation.detail

import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.util.MonsterResult
import kotlinx.coroutines.flow.Flow

data class DetailState(
    val index: String = "",
    val monster: MonsterResult<Monster>
)
