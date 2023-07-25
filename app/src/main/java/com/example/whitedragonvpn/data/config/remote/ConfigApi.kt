package com.example.whitedragonvpn.data.config.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ConfigApi {
    @GET("/config/{code}")
    suspend fun getConfiguration(@Path(value = "code") countryCode: String): ConfigResponse
}