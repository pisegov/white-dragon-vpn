package com.example.whitedragonvpn.ui.settings_fragment.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitedragonvpn.data.settings.store.SettingsRepository
import com.example.whitedragonvpn.store.protos.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    fun getSettingsFlow(): Flow<Settings> {
        return settingsRepository.getSettingsFlow()
    }


    fun storeProtocol(protocol: String) {
        viewModelScope.launch {
            settingsRepository.storeProtocol(protocol)
        }
    }

    fun storeUseHomeCountry(useHomeCountry: Boolean) {
        viewModelScope.launch {
            settingsRepository.storeUseHomeCountry(useHomeCountry)
        }
    }

    fun storeApplicationLanguage(language: String) {
        viewModelScope.launch {
            settingsRepository.storeApplicationLanguage(language)
        }
    }
}
