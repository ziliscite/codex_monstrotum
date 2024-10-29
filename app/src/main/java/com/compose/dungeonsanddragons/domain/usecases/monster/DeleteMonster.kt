package com.compose.dungeonsanddragons.domain.usecases.monster

import com.compose.dungeonsanddragons.data.local.room.MonsterDao

class DeleteMonster(
    private val monsterDao: MonsterDao
) {
    suspend operator fun invoke(index: String) {
        monsterDao.deleteMonster(index)
    }
}