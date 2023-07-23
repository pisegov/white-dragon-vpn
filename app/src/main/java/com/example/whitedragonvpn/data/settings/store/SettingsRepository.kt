package com.example.whitedragonvpn.data.settings.store

import androidx.datastore.core.DataStore
import com.example.whitedragonvpn.store.protos.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val dataStore: DataStore<Settings>) {
    fun getSettingsFlow(): Flow<Settings> {
        return dataStore.data
    }


    suspend fun storeProtocol(protocol: String) = withContext(Dispatchers.IO) {
        dataStore.updateData { settings ->
            settings.toBuilder()
                .setProtocol(protocol)
                .build()
        }
    }

    suspend fun storeUseHomeCountry(useHomeCountry: Boolean) = withContext(Dispatchers.IO) {
        dataStore.updateData { settings ->
            settings.toBuilder()
                .setUseHomeCountry(useHomeCountry)
                .build()
        }
    }

    suspend fun storeApplicationLanguage(language: String) = withContext(Dispatchers.IO) {
        dataStore.updateData { settings ->
            settings.toBuilder()
                .setApplicationLanguage(language)
                .build()
        }
    }
}