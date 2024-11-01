package com.compose.dungeonsanddragons.data.remote.service

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.MonsterResult
import com.compose.dungeonsanddragons.util.loadErrorResult
import com.compose.dungeonsanddragons.util.retryCall
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
                throw NoSuchElementException("No monsters found")
            }

            // Return the page result
            LoadResult.Page(
                data = monstersPage,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (monstersPage.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e.loadErrorResult())
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