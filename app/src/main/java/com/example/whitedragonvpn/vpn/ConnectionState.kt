package com.example.whitedragonvpn.vpn

import com.wireguard.android.backend.Tunnel

data class ConnectionState(
    val state: Tunnel.State,
    val countryCode: String
)
