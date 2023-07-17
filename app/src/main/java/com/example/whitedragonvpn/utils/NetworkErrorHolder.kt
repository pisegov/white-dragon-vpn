package com.example.whitedragonvpn.utils

import com.example.whitedragonvpn.data.model.ConfigModel
import com.example.whitedragonvpn.data.remote.retrofit.model.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object NetworkErrorHolder {
    private val _error =
        MutableStateFlow<NetworkResult<ConfigModel>?>(null)
    val error: StateFlow<NetworkResult<ConfigModel>?> = _error

    suspend fun setError(e: NetworkResult.GenericError?) {
        _error.emit(e)
    }
}