package com.example.whitedragonvpn.data.countries

import com.example.whitedragonvpn.data.Repository
import com.example.whitedragonvpn.data.countries.remote.NetworkCountriesSource
import com.example.whitedragonvpn.data.remote.retrofit.NetworkResult
import com.example.whitedragonvpn.data.resources.ResourceProvider
import com.example.whitedragonvpn.data.settings.store.SettingsRepository
import com.example.whitedragonvpn.ioc.ApplicationScope
import com.example.whitedragonvpn.utils.NetworkErrorHolder
import kotlinx.coroutines.flow.first
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
@ApplicationScope
class CountriesRepository @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val settingsRepository: SettingsRepository,
    private val networkCountriesSource: NetworkCountriesSource
) : Repository() {
    suspend fun getCountriesList(): List<CountryModel>? {

        val locale = getApplicationLocale()
        val networkResult = safeApiCall { networkCountriesSource.getCountriesList(locale) }

        return when (networkResult) {
            is NetworkResult.Success -> networkResult.data
            else -> {
                NetworkErrorHolder.setError(networkResult as NetworkResult.GenericError)
                null
            }
        }
    }

    private suspend fun getApplicationLocale(): String {
        val preferenceValue: String =
            settingsRepository.getSettingsFlow().first().applicationLanguage
        if (preferenceValue == resourceProvider.systemLanguagePermissionCode) {
            return resourceProvider.getSystemLocale()
        }

        return preferenceValue
    }
}