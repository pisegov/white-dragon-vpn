package com.example.whitedragonvpn.data.config.remote

import kotlinx.serialization.Serializable

@Serializable
data class ConfigResponse(
    val address: String,
    val peerPrivateKey: String,
    val endpoint: String,
    val allowedIps: String,
    val serverPublicKey: String,
    val countryCode: String,
)