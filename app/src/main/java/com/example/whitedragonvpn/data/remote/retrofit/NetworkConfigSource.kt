package com.example.whitedragonvpn.data.remote.retrofit

import com.example.whitedragonvpn.data.model.ConfigModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class NetworkConfigSource {
    suspend fun getConfig(): ConfigModel =
        withContext(Dispatchers.IO) {
            val response = RetrofitModule.configApi.getConfiguration()
            return@withContext ConfigModel(
                address = response.address,
                peerPrivateKey = response.peerPrivateKey,
                endpoint = response.endpoint,
                allowedIps = response.allowedIps,
                serverPublicKey = response.serverPublicKey
            )
        }
}