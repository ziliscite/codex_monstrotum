package com.compose.dungeonsanddragons.data.remote.service

import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.MonsterList
import retrofit2.http.GET
import retrofit2.http.Path

interface MonsterApi {
    @GET("monsters")
    suspend fun getAllMonsters(): MonsterList

    @GET("monsters/{index}")
    suspend fun getMonsterByIndex(
        @Path("index") index: String
    ): Monster
}
