package com.example.whitedragonvpn.data.config.remote

import com.example.whitedragonvpn.data.config.ConfigModel
import com.example.whitedragonvpn.data.remote.retrofit.RetrofitModule
import com.example.whitedragonvpn.ioc.ApplicationScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@ApplicationScope
class NetworkConfigSource @Inject constructor() {
    suspend fun getConfig(countryCode: String): ConfigModel =
        withContext(Dispatchers.IO) {
            val response = RetrofitModule.configApi.getConfiguration(countryCode)

            return@withContext ConfigModel(
                address = response.address,
                peerPrivateKey = response.peerPrivateKey,
                endpoint = response.endpoint,
                allowedIps = response.allowedIps,
                serverPublicKey = response.serverPublicKey,
                countryCode = response.countryCode
            )
        }
}
