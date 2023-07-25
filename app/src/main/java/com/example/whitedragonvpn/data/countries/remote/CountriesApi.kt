package com.example.whitedragonvpn.data.countries.remote

import com.example.whitedragonvpn.data.remote.retrofit.model.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {
    @GET("/countries/{app_locale}")
    suspend fun getCountriesList(@Path(value = "app_locale") applicationLocale: String): List<CountryResponse>
}