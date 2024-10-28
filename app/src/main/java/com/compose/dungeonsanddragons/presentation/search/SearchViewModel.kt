package com.compose.dungeonsanddragons.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.compose.dungeonsanddragons.domain.usecases.monster.MonsterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val monsterUseCases: MonsterUseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf(SearchState())
    val searchQuery: State<SearchState> = _searchQuery

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _searchQuery.value = searchQuery.value.copy(
                    searchQuery = event.query
                )
            }

            is SearchEvent.SearchMonsters -> {
                searchMonsters()
            }
        }
    }

    private fun searchMonsters() {
        val monsters = monsterUseCases.searchMonsters(searchQuery.value.searchQuery).cachedIn(viewModelScope)
        _searchQuery.value = searchQuery.value.copy(
            monsters = monsters
        )
    }
}