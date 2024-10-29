package com.compose.dungeonsanddragons.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity

@Database(entities = [MonsterEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MonsterDatabase : RoomDatabase() {
    abstract val monsterDao: MonsterDao
}
