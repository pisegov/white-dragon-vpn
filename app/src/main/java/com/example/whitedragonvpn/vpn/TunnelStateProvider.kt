package com.example.whitedragonvpn.vpn

class TunnelStateProvider(private val tunnel: WgTunnel) {
    fun getStateObservable() = tunnel.state
    fun getCurrentCountryObservable() = tunnel.currentCountryObservable
}