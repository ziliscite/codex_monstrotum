package com.compose.dungeonsanddragons.presentation.detail

sealed class DetailEvent {
    data class GetMonster(val index: String) : DetailEvent()
    data object SaveArticle : DetailEvent()
}
