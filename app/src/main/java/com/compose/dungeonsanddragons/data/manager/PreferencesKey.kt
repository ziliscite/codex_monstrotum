package com.compose.dungeonsanddragons.data.manager

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.compose.dungeonsanddragons.util.Constants

internal object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(Constants.USER_SETTINGS)
}