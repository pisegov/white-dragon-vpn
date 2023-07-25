package com.example.whitedragonvpn.data.countries.remote

import com.example.whitedragonvpn.data.countries.CountryModel
import com.example.whitedragonvpn.data.remote.retrofit.RetrofitModule
import com.example.whitedragonvpn.ioc.ApplicationScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@ApplicationScope
class NetworkCountriesSource @Inject constructor() {
    suspend fun getCountriesList(applicationLocaleCode: String): List<CountryModel> =
        withContext(Dispatchers.IO) {
            val response = RetrofitModule.countriesApi.getCountriesList(applicationLocaleCode)
            return@withContext response.map { CountryModel(it.code, it.localizedName) }
        }
}
