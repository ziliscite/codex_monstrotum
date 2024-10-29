package com.compose.dungeonsanddragons.domain.usecases.monster

data class MonsterUseCases(
    val getMonsters: GetMonsters,
    val getMonsterByIndex: GetMonsterByIndex,
    val searchMonsters: SearchMonsters,
    val upsertMonster: UpsertMonster,
    val deleteMonster: DeleteMonster,
    val getFavoriteMonsters: GetFavoriteMonsters,
    val getFavoriteMonsterByIndex: GetFavoriteMonsterByIndex
)
