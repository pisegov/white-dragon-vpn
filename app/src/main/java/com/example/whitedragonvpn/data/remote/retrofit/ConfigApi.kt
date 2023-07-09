package com.example.whitedragonvpn.data.remote.retrofit

import com.example.whitedragonvpn.data.remote.retrofit.model.ConfigResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ConfigApi {
    @GET("/config/{code}")
    suspend fun getConfiguration(@Path(value = "code") countryCode: String): ConfigResponse
}