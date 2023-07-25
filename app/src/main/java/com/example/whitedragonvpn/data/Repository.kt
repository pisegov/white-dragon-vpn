package com.example.whitedragonvpn.data

import com.example.whitedragonvpn.data.remote.retrofit.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class Repository {
    protected suspend fun <T : Any> safeApiCall(
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