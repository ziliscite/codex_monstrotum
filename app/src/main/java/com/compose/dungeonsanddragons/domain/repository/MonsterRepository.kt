package com.compose.dungeonsanddragons.domain.repository

import androidx.paging.PagingData
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.MonsterResult
import kotlinx.coroutines.flow.Flow

// It should've been List<Monster>
interface MonsterRepository{
    fun getMonsterList() : Flow<PagingData<ResultsItem>>
    fun searchMonsters(query: String): Flow<PagingData<ResultsItem>>
    fun getMonsterByIndex(index : String) : Flow<MonsterResult<Monster>>
}
