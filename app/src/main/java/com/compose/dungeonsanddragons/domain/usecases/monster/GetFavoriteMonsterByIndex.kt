package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.local.room.MonsterDao
import kotlinx.coroutines.flow.Flow

class GetFavoriteMonsterByIndex (
    private val monsterDao: MonsterDao
) {
    operator fun invoke(index: String): Flow<MonsterEntity> {
        return monsterDao.getMonsterByIndex(index)
    }
}
