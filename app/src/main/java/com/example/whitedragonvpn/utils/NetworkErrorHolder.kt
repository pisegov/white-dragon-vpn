package com.example.whitedragonvpn.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whitedragonvpn.data.model.ConfigModel
import com.example.whitedragonvpn.data.remote.retrofit.model.NetworkResult

object NetworkErrorHolder {
    private val _error = MutableLiveData<NetworkResult<ConfigModel>>()
    val error: LiveData<NetworkResult<ConfigModel>> = _error

    fun setError(e: NetworkResult.GenericError) {
        _error.postValue(e)
    }
}