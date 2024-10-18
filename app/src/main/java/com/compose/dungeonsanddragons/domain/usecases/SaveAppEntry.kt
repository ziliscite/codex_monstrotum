package com.compose.dungeonsanddragons.domain.usecases

import com.compose.dungeonsanddragons.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
