package com.compose.dungeonsanddragons.domain.usecases.appentry

import com.compose.dungeonsanddragons.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {
    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}
