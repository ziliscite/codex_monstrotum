package com.compose.dungeonsanddragons.di

import com.compose.dungeonsanddragons.domain.manager.LocalUserManager
import com.compose.dungeonsanddragons.domain.usecases.AppEntryUseCases
import com.compose.dungeonsanddragons.domain.usecases.ReadAppEntry
import com.compose.dungeonsanddragons.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }
}
