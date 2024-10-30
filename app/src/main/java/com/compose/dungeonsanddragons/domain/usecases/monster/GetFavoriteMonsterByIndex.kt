package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.local.room.MonsterDao
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMonsterByIndex (
    private val monsterRepository: MonsterRepository
) {
    operator fun invoke(index: String): Flow<MonsterEntity> {
        return monsterRepository.getFavoriteMonsterByIndex(index)
    }
}
