package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.local.room.MonsterDao
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.toEntity

class UpsertMonster(
    private val monsterDao: MonsterDao
) {
    suspend operator fun invoke(monster: MonsterEntity) {
        monsterDao.upsert(monster)
    }

    suspend operator fun invoke(monster: Monster) {
        monsterDao.upsert(monster.toEntity())
    }
}
