package com.compose.dungeonsanddragons.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.compose.dungeonsanddragons.util.Constants

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_SETTINGS)