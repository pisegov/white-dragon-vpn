package com.example.whitedragonvpn.data.remote.retrofit

import com.example.whitedragonvpn.data.remote.retrofit.model.ConfigResponse
import retrofit2.http.GET

interface ConfigApi {
    @GET("/config")
    suspend fun getConfiguration(): ConfigResponse
}