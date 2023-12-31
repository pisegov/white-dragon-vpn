package com.example.whitedragonvpn.data.config

data class ConfigModel(
    val address: String,
    val peerPrivateKey: String,
    val endpoint: String,
    val allowedIps: String,
    val serverPublicKey: String,
    val countryCode: String
)
