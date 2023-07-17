package com.example.whitedragonvpn.ui.shared_components

import androidx.lifecycle.ViewModel
import com.example.whitedragonvpn.vpn.TunnelManager
import com.example.whitedragonvpn.vpn.TunnelStateProvider
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BaseViewModel @Inject constructor(
    private val tunnelManager: TunnelManager,
    private val stateProvider: TunnelStateProvider
) : ViewModel() {
    fun getCurrentTunnelState(): StateFlow<Tunnel.State> {
        return stateProvider.getStateFlow()
    }

    fun getCurrentCountryCode(): StateFlow<String> {
        return stateProvider.getCurrentCountryFlow()
    }
}