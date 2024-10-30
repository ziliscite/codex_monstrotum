package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.local.room.MonsterDao
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.toEntity
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository

class UpsertMonster(
    private val monsterRepository: MonsterRepository
) {
    suspend operator fun invoke(monster: MonsterEntity) {
        monsterRepository.upsert(monster)
    }

    suspend operator fun invoke(monster: Monster) {
        monsterRepository.upsert(monster.toEntity())
    }
}
