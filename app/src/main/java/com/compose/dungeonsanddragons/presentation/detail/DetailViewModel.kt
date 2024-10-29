package com.compose.dungeonsanddragons.presentation.detail


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.domain.usecases.monster.MonsterUseCases
import com.compose.dungeonsanddragons.util.MonsterResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val monsterUseCases: MonsterUseCases
) : ViewModel() {
    private val _monster = mutableStateOf(DetailState(monster = MonsterResult.Loading))
    val monster: State<DetailState> = _monster

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetMonster -> {
                getMonster(event.index).onEach {
                    _monster.value = monster.value.copy(monster = it)
                }.launchIn(viewModelScope)
            }
            is DetailEvent.SaveArticle -> {
                // TODO: Save monster to database
            }
        }
    }

    private fun getMonster(index: String): Flow<MonsterResult<Monster>> {
        return monsterUseCases.getMonsterByIndex(index)
    }
}
