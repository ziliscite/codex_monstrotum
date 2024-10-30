package com.compose.dungeonsanddragons.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.local.room.MonsterDao
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.data.remote.service.MonsterApi
import com.compose.dungeonsanddragons.data.remote.service.MonsterPagingSource
import com.compose.dungeonsanddragons.data.remote.service.SearchPagingSource
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import com.compose.dungeonsanddragons.util.MonsterResult
import com.compose.dungeonsanddragons.util.loadErrorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MonsterRepositoryImpl @Inject constructor(
    private val monsterApi: MonsterApi,
    private val monsterDao: MonsterDao
) : MonsterRepository{
    override fun getMonsterByIndex(index: String): Flow<MonsterResult<Monster>> = flow {
        emit(MonsterResult.Loading)
        try {
            val monster = monsterApi.getMonsterByIndex(index)
            Log.d("MonsterUseCase", "Successfully fetched monster: ${monster.name}")
            emit(MonsterResult.Success(monster))
        } catch (e: Exception) {
            Log.e("MonsterUseCase", "Error fetching monster", e)
            emit(MonsterResult.Failed(e.loadErrorResult().message.toString()))
        }
    }

    override fun getMonsterList(): Flow<PagingData<ResultsItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MonsterPagingSource(monsterApi)
            }
        ).flow
    }

    override fun searchMonsters(query: String): Flow<PagingData<ResultsItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchPagingSource(monsterApi, query)
            }
        ).flow
    }

    override fun getFavoriteMonsters(): Flow<List<MonsterEntity>> = monsterDao.getMonsters()
    override fun getFavoriteMonsterByIndex(index: String): Flow<MonsterEntity> = monsterDao.getMonsterByIndex(index)
    override suspend fun upsert(monster: MonsterEntity) = monsterDao.upsert(monster)
    override suspend fun deleteMonster(index: String) = monsterDao.deleteMonster(index)
}
