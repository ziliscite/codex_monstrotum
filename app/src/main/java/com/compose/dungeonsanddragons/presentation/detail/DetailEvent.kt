package com.compose.dungeonsanddragons.presentation.detail

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity

sealed class DetailEvent {
    data class GetMonster(val index: String) : DetailEvent()
    data class GetFavoriteMonster(val index: String) : DetailEvent()
    data class UpsertDeleteMonster(val monster: MonsterEntity) : DetailEvent()
    data object RemoveSideEffect : DetailEvent()
}
