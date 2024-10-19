package com.compose.dungeonsanddragons.di

import com.compose.dungeonsanddragons.data.manager.LocalUserManagerImpl
import com.compose.dungeonsanddragons.domain.manager.LocalUserManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalUserManagerModule {

    @Binds
    @Singleton
    abstract fun bindLocalUserManager(
        localUserManagerImpl: LocalUserManagerImpl
    ) : LocalUserManager
}
