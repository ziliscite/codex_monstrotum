package com.compose.dungeonsanddragons.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MonsterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(monster: MonsterEntity)

    @Query("SELECT * FROM monsterentity")
    fun getMonsters(): Flow<List<MonsterEntity>>

    @Query("DELETE FROM monsterentity where id = :index")
    suspend fun deleteMonster(index: String)
}
