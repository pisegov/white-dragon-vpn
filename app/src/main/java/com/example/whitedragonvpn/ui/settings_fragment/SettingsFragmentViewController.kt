package com.example.whitedragonvpn.ui.settings_fragment

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.preference.ListPreference
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsFragmentViewScope
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SettingsFragmentViewScope
class SettingsFragmentViewController @Inject constructor(
    private val preferenceManager: PreferenceManager,
    lifecycleOwner: LifecycleOwner,
    private val viewModel: SettingsViewModel
) {
    companion object {
        const val PROTOCOL_KEY = "protocol"
        const val USE_HOME_COUNTRY_KEY = "useHomeCountry"
        const val LANGUAGE_KEY = "language"
    }

    private val lifecycleScope = lifecycleOwner.lifecycleScope

    private var protocolPreference: ListPreference? =
        preferenceManager.findPreference(PROTOCOL_KEY)
    private var useHomeCountryPreference: SwitchPreferenceCompat? =
        preferenceManager.findPreference(USE_HOME_COUNTRY_KEY)
    private var languagePreference: ListPreference? =
        preferenceManager.findPreference(LANGUAGE_KEY)

    fun setupPreferences() {
        protocolPreference?.apply {
            setOnPreferenceChangeListener { _, newValue ->
                lifecycleScope.launch {
                    viewModel.storeProtocol(newValue as String)
                }
                return@setOnPreferenceChangeListener true
            }
        }

        useHomeCountryPreference?.apply {
            setOnPreferenceChangeListener { _, newValue ->
                lifecycleScope.launch {
                    viewModel.storeUseHomeCountry(newValue as Boolean)
                }
                return@setOnPreferenceChangeListener true
            }
        }
        languagePreference?.apply {
            setOnPreferenceChangeListener { _, newValue ->
                lifecycleScope.launch {
                    viewModel.storeApplicationLanguage(newValue as String)
                }
                return@setOnPreferenceChangeListener true
            }
        }

        collectPreferencesState()
    }

    private fun collectPreferencesState() {
        lifecycleScope.launch {
            viewModel.getSettingsFlow().collect { settings ->
                protocolPreference?.value = settings.protocol
                useHomeCountryPreference?.isChecked = settings.useHomeCountry
                languagePreference?.value = settings.applicationLanguage
            }
        }
    }
}