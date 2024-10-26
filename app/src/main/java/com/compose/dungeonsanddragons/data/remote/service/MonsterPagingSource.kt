package com.compose.dungeonsanddragons.data.remote.service

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.MonsterResult
import com.google.gson.JsonParseException
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

// PagingSource for monsters
class MonsterPagingSource(
    private val monsterApi: MonsterApi
) : PagingSource<Int, ResultsItem>() {
    private val pageSize = 20
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        val page = params.key ?: 1

        return try {
            // Fetching the list of monsters
            val response = retryCall {
                monsterApi.getAllMonsters()
            }

            // Calculate start and end index
            val startIndex = (page - 1) * pageSize
            val endIndex = minOf(startIndex + pageSize, response.count)

            // Check if startIndex is out of bounds
            if (startIndex >= response.results.size) {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = null
                )
            }

            // Get the sublist for the current page
            val monstersPage = response.results.subList(startIndex, endIndex)

            if (monstersPage.isEmpty()) {
                throw(NoSuchElementException("No monsters found"))
            }

            // Return the page result
            LoadResult.Page(
                data = monstersPage,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (monstersPage.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(Exception("Lost in the wilderness. No network found—seek the path anew."))
        } catch (e: HttpException) {
            LoadResult.Error(Exception("The server dragon roars: ${e.code()}. A retry may quell its fury."))
        } catch (e: TimeoutException) {
            LoadResult.Error(Exception("The portal timed out... check your connection to the realm."))
        } catch (e: JsonParseException) {
            LoadResult.Error(Exception("The scrolls are jumbled. Try refreshing to decipher the runes."))
        } catch (e: NoSuchElementException) {
            LoadResult.Error(Exception(e.message ?: "No monsters found"))
        } catch (e: Exception) {
            LoadResult.Error(Exception("An unexpected fate: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        // Refresh key logic: return the closest anchor page
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

// Retry logic with a configurable delay and retry count
private suspend fun <T> retryCall(
    retryCount: Int = 5,
    delayMillis: Long = 1500,
    block: suspend () -> T
): T {
    repeat(retryCount - 1) {
        try {
            return block()
        } catch (e: IOException) {
            delay(delayMillis)
        } catch (e: TimeoutException) {
            delay(delayMillis)
        }
    }
    return block() // Last attempt without catching exceptions
}
