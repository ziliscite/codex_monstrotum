package com.compose.dungeonsanddragons.domain.usecases.monster

data class MonsterUseCases(
    val getMonsters: GetMonsters,
    val getMonsterByIndex: GetMonsterByIndex,
    val searchMonsters: SearchMonsters
)
