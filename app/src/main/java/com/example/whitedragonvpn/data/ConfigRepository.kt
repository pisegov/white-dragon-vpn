package com.example.whitedragonvpn.data

import com.example.whitedragonvpn.data.model.ConfigModel
import com.example.whitedragonvpn.data.remote.retrofit.NetworkConfigSource
import com.example.whitedragonvpn.data.remote.retrofit.model.NetworkResult
import com.example.whitedragonvpn.utils.NetworkErrorHolder
import com.wireguard.config.Config
import com.wireguard.config.InetEndpoint
import com.wireguard.config.InetNetwork
import com.wireguard.config.Interface
import com.wireguard.config.Peer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalSerializationApi::class)
class ConfigRepository(private val networkConfigSource: NetworkConfigSource) {
    suspend fun getConfig(): Config? {
        val configModel = getNetworkConfig()
        return configModel?.let { config ->
            buildConfig(config)
        }
    }

    private suspend fun getNetworkConfig(): ConfigModel? {
        val networkResult = safeApiCall { networkConfigSource.getConfig() }
        return when (networkResult) {
            is NetworkResult.Success -> networkResult.data
            else -> {
                NetworkErrorHolder.setError(networkResult as NetworkResult.GenericError)
                null
            }
        }
    }

    private fun buildConfig(configModel: ConfigModel): Config {
        val interfaceBuilder = Interface.Builder()
        val peerBuilder = Peer.Builder()
        return Config.Builder()
            .setInterface(
                interfaceBuilder.addAddress(InetNetwork.parse(configModel.address))
                    .parsePrivateKey(configModel.peerPrivateKey)
                    .build()
            )
            .addPeer(
                peerBuilder.addAllowedIp(InetNetwork.parse(configModel.allowedIps))
                    .setEndpoint(
                        InetEndpoint.parse(configModel.endpoint)
                    ).parsePublicKey(configModel.serverPublicKey)
                    .setPersistentKeepalive(20)
                    .build()
            )
            .build()
    }

    private suspend fun <T : Any> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResult.Success(apiCall.invoke())
            } catch (error: Throwable) {
                when (error) {
                    is IOException -> {
                        NetworkResult.GenericError("Network Error. Are you connected?")
                    }

                    is HttpException -> {
                        val response = error.response()?.errorBody()?.source().toString()
                        NetworkResult.GenericError(response)
                    }

                    else -> {
                        NetworkResult.GenericError("Undefined Error")
                    }
                }
            }
        }
    }
}

