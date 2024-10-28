package com.compose.dungeonsanddragons.domain.usecases.monster

import androidx.paging.PagingData
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import kotlinx.coroutines.flow.Flow

class SearchMonsters(
    private val monsterRepository: MonsterRepository
) {
    operator fun invoke(query: String): Flow<PagingData<ResultsItem>> {
        return monsterRepository.searchMonsters(query)
    }
}
