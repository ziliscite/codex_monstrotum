package com.compose.dungeonsanddragons.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.compose.dungeonsanddragons.domain.usecases.monster.MonsterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val monsterUseCases: MonsterUseCases
) : ViewModel() {
    val monsters = monsterUseCases.getMonsters().cachedIn(viewModelScope)
}
