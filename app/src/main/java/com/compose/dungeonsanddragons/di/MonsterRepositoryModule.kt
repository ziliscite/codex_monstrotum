package com.compose.dungeonsanddragons.di

import com.compose.dungeonsanddragons.data.repository.MonsterRepositoryImpl
import com.compose.dungeonsanddragons.domain.repository.MonsterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MonsterRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMonsterRepository(
        monsterRepositoryImpl: MonsterRepositoryImpl
    ) : MonsterRepository
}
