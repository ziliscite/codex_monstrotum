package com.compose.dungeonsanddragons.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.compose.dungeonsanddragons.domain.manager.LocalUserManager
import com.compose.dungeonsanddragons.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_SETTINGS)

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

private object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(Constants.USER_SETTINGS)
}
