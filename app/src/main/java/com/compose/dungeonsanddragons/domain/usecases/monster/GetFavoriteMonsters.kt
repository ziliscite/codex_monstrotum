package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.local.room.MonsterDao
import kotlinx.coroutines.flow.Flow

class GetFavoriteMonsters (
    private val monsterDao: MonsterDao
) {
    operator fun invoke(): Flow<List<MonsterEntity>> {
        return monsterDao.getMonsters()
    }
}