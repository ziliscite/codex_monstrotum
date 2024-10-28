package com.compose.dungeonsanddragons.presentation.search

import androidx.paging.PagingData
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val monsters: Flow<PagingData<ResultsItem>>? = null
)
