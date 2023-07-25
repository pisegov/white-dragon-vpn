package com.example.whitedragonvpn.data.remote.retrofit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    @SerialName("code")
    val code: String,
    @SerialName("localized_name")
    val localizedName: String
)
