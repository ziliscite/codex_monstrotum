package com.compose.dungeonsanddragons.data.remote.service

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.util.loadErrorResult
import com.compose.dungeonsanddragons.util.retryCall

class SearchPagingSource(
    private val monsterApi: MonsterApi,
    private val query: String
) : PagingSource<Int, ResultsItem>() {
    private val pageSize = 20

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        val page = params.key ?: 1
        return try {
            val response = retryCall {
                monsterApi.getAllMonsters()
            }

            // Filter monsters based on the query
            val monsters = response.results.filter {
                it.name.contains(query, ignoreCase = true)
            }

            if (monsters.isEmpty()) {
                throw NoSuchElementException("No monsters with the name '$query' found")
            }

            // Calculate start and end index
            val startIndex = (page - 1) * pageSize
            val endIndex = minOf(startIndex + pageSize, monsters.size)

            // Check if startIndex is out of bounds
            if (startIndex >= monsters.size) {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = null
                )
            }

            val monstersPage = monsters.subList(startIndex, endIndex)

            LoadResult.Page(
                data = monstersPage,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == monstersPage.size) null else page + 1
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