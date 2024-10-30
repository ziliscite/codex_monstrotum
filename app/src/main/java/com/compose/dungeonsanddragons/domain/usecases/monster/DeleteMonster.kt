package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.domain.repository.MonsterRepository

class DeleteMonster(
    private val monsterRepository: MonsterRepository
) {
    suspend operator fun invoke(index: String) {
        monsterRepository.deleteMonster(index)
    }
}