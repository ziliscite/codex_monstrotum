package com.compose.dungeonsanddragons.domain.repository

import androidx.paging.PagingData
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.MonsterResult
import kotlinx.coroutines.flow.Flow

interface MonsterRepository{
    fun getMonsterList() : Flow<PagingData<ResultsItem>>
    fun searchMonsters(query: String): Flow<PagingData<ResultsItem>>
    fun getMonsterByIndex(index : String) : Flow<MonsterResult<Monster>>
    fun getFavoriteMonsters(): Flow<List<MonsterEntity>>
    fun getFavoriteMonsterByIndex(index: String): Flow<MonsterEntity>
    suspend fun upsert(monster: MonsterEntity)
    suspend fun deleteMonster(index: String)
}
