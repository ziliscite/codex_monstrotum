package com.compose.dungeonsanddragons.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.dungeonsanddragons.domain.usecases.monster.MonsterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val monsterUseCases: MonsterUseCases
): ViewModel() {
    private val _state = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> = _state

    init {
        getFavoriteMonsters()
    }

    private fun getFavoriteMonsters() {
        monsterUseCases.getFavoriteMonsters().onEach {
            _state.value = state.value.copy(
                monsters = it
            )
        }.launchIn(viewModelScope)
    }
}
