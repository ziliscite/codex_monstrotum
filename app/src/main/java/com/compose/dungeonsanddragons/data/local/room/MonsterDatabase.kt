package com.compose.dungeonsanddragons.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity

@Database(entities = [MonsterEntity::class], version = 1)
abstract class MonsterDatabase : RoomDatabase() {
    abstract val monsterDao: MonsterDao
}
