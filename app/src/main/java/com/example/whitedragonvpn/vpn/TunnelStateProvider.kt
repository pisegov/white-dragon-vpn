package com.example.whitedragonvpn.vpn

import com.example.whitedragonvpn.ioc.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class TunnelStateProvider @Inject constructor(private val tunnel: WgTunnel) {
    fun getConnectionStateFlow() = tunnel.connectionState
}