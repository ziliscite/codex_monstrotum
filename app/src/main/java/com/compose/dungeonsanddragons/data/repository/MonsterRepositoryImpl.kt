package com.compose.dungeonsanddragons.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.data.remote.service.MonsterApi
import com.compose.dungeonsanddragons.data.remote.service.MonsterPagingSource
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import com.compose.dungeonsanddragons.util.MonsterResult
import com.google.gson.JsonParseException
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class MonsterRepositoryImpl @Inject constructor(
    private val monsterApi: MonsterApi
) : MonsterRepository{
    override suspend fun getMonsterByIndex(index: String): MonsterResult<Monster> {
        return handleErrors {
            monsterApi.getMonsterByIndex(index)
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

    suspend fun searchMonsters(query: String): MonsterResult<List<ResultsItem>> {
        return handleErrors {
            val response = monsterApi.getAllMonsters()

            // Filter monsters based on the query
            val monstersPage = response.results.filter {
                it.name.contains(query, ignoreCase = true)
            }

            if (monstersPage.isEmpty()) {
                throw NoSuchElementException("No monsters with the name '$query' found")
            }

            monstersPage
        }
    }
}

private suspend fun <T> handleErrors(call: suspend () -> T): MonsterResult<T> {
    return try {
        val response = call()
        MonsterResult.Success(response)
    } catch (e: IOException) {
        MonsterResult.Failed("No internet connection. Please check your network.")
    } catch (e: HttpException) {
        MonsterResult.Failed("Server error: ${e.code()}. Please try again later.")
    } catch (e: TimeoutException) {
        MonsterResult.Failed("Request timed out. Consider checking your connection.")
    } catch (e: JsonParseException) {
        MonsterResult.Failed("Data parsing issue. Try refreshing the app.")
    } catch (e: NoSuchElementException) {
        MonsterResult.Failed(e.message ?: "No monsters found")
    } catch (e: Exception) {
        MonsterResult.Failed("Unexpected error: ${e.localizedMessage ?: "Unknown error"}")
    }
}