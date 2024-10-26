package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import com.compose.dungeonsanddragons.util.MonsterResult

class GetMonsterByIndex(private val monsterRepository: MonsterRepository) {
    suspend operator fun invoke(index: String): MonsterResult<Monster> {
        return monsterRepository.getMonsterByIndex(index)
    }
}
