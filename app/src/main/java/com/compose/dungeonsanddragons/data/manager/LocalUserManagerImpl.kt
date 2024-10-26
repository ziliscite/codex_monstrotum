package com.compose.dungeonsanddragons.data.manager

import android.app.Application
import androidx.datastore.preferences.core.edit
import com.compose.dungeonsanddragons.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor(
    private val context: Application
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit {
            it[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferencesKey.APP_ENTRY] ?: false
        }
    }
}
