package com.example.whitedragonvpn.data.remote.retrofit

import com.example.whitedragonvpn.BuildConfig
import com.example.whitedragonvpn.data.config.remote.ConfigApi
import com.example.whitedragonvpn.data.countries.remote.CountriesApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@ExperimentalSerializationApi
object RetrofitModule {
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val httpClient = OkHttpClient().newBuilder()
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
    val configApi: ConfigApi = retrofit.create<ConfigApi>()
    val countriesApi: CountriesApi = retrofit.create<CountriesApi>()


    fun clearConnectionPool() {
        httpClient.connectionPool.evictAll()
    }
}