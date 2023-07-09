package com.example.whitedragonvpn.vpn

import com.wireguard.android.backend.Tunnel

interface TunnelLauncher {
    suspend fun toggleTunnelState()
    suspend fun switchTunnelState(state: Tunnel.State, countryCode: String)
}