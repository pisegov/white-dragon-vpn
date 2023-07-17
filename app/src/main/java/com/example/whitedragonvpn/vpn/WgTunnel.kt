package com.example.whitedragonvpn.vpn

import com.example.whitedragonvpn.ioc.ApplicationScope
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@ApplicationScope
class WgTunnel @Inject constructor() : Tunnel {
    private val _state = MutableStateFlow(Tunnel.State.DOWN)
    val state: StateFlow<Tunnel.State> = _state

    private val currentCountry = MutableStateFlow<String>("ne")
    val currentCountryObservable: StateFlow<String> = currentCountry
    override fun getName(): String {
        return "wgpreconf"
    }

    override fun onStateChange(newState: Tunnel.State) {
        _state.update { newState }
    }

    fun updateCountryCode(countryCode: String) {
        currentCountry.update { countryCode }
    }
}