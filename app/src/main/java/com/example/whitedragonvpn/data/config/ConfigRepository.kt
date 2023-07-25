package com.example.whitedragonvpn.data.config

import com.example.whitedragonvpn.data.Repository
import com.example.whitedragonvpn.data.config.remote.NetworkConfigSource
import com.example.whitedragonvpn.data.remote.retrofit.NetworkResult
import com.example.whitedragonvpn.ioc.ApplicationScope
import com.example.whitedragonvpn.utils.NetworkErrorHolder
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@OptIn(ExperimentalSerializationApi::class)
@ApplicationScope
class ConfigRepository @Inject constructor(private val networkConfigSource: NetworkConfigSource) :
    Repository() {

    suspend fun getConfig(countryCode: String): ConfigModel? {
        val networkResult = safeApiCall { networkConfigSource.getConfig(countryCode) }
        return when (networkResult) {
            is NetworkResult.Success -> networkResult.data
            else -> {
                NetworkErrorHolder.setError(networkResult as NetworkResult.GenericError)
                null
            }
        }
    }
}

