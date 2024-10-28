package com.compose.dungeonsanddragons.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val query: String) : SearchEvent()
    data object SearchMonsters : SearchEvent()
}
