package com.example.whitedragonvpn.data

import com.example.whitedragonvpn.data.model.ConfigModel
import com.example.whitedragonvpn.data.remote.retrofit.NetworkConfigSource
import com.example.whitedragonvpn.data.remote.retrofit.model.NetworkResult
import com.example.whitedragonvpn.utils.NetworkErrorHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalSerializationApi::class)
class ConfigRepository(private val networkConfigSource: NetworkConfigSource) {

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

