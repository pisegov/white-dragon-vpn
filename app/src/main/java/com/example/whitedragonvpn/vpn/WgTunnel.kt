package com.example.whitedragonvpn.vpn

import com.example.whitedragonvpn.ioc.ApplicationScope
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@ApplicationScope
class WgTunnel @Inject constructor() : Tunnel {

    private val _connectionState =
        MutableStateFlow<ConnectionState>(ConnectionState(Tunnel.State.DOWN, "ne"))
    val connectionState: StateFlow<ConnectionState> = _connectionState
    override fun getName(): String {
        return "wgpreconf"
    }

    override fun onStateChange(newState: Tunnel.State) {
        _connectionState.update { ConnectionState(newState, it.countryCode) }
    }

    fun updateCountryCode(countryCode: String) {
        _connectionState.update { ConnectionState(it.state, countryCode) }
    }
}