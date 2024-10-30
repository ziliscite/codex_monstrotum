package com.compose.dungeonsanddragons.presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.domain.usecases.monster.MonsterUseCases
import com.compose.dungeonsanddragons.util.MonsterResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val monsterUseCases: MonsterUseCases,
) : ViewModel() {
    private val _monster = mutableStateOf(DetailState(monster = MonsterResult.Loading))
    val monster: State<DetailState> = _monster

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetFavoriteMonster -> {
                getMonsterFavorite(event.index).onEach { result ->
                    _monster.value = monster.value.copy(favoriteMonster = result)
                }.launchIn(viewModelScope)
            }
            is DetailEvent.GetMonster -> {
                getMonster(event.index).onEach { result ->
                    _monster.value = monster.value.copy(monster = result)
                }.launchIn(viewModelScope)
            }
            is DetailEvent.UpsertDeleteMonster -> { viewModelScope.launch {
                if (monster.value.favoriteMonster != null) {
                    deleteMonster(event.monster)
                } else {
                    upsertMonster(event.monster)
                }
            }}
            is DetailEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private fun getMonster(index: String): Flow<MonsterResult<Monster>> {
        return monsterUseCases.getMonsterByIndex(index)
    }

    private fun getMonsterFavorite(index: String): Flow<MonsterEntity?> {
        return monsterUseCases.getFavoriteMonsterByIndex(index)
    }

    private suspend fun upsertMonster(monster: MonsterEntity) {
        monsterUseCases.upsertMonster(monster)
        sideEffect = "${monster.name} has been saved as favorite"
    }

    private suspend fun deleteMonster(monster: MonsterEntity) {
        monsterUseCases.deleteMonster(monster.index)
        sideEffect = "${monster.name} has been deleted from favorites"
    }
}
