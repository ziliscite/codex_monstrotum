package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import com.compose.dungeonsanddragons.util.MonsterResult
import kotlinx.coroutines.flow.Flow

class GetMonsterByIndex(
    private val monsterRepository: MonsterRepository
) {
    operator fun invoke(index: String): Flow<MonsterResult<Monster>> {
        return monsterRepository.getMonsterByIndex(index)
    }
}
