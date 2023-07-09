package com.example.whitedragonvpn.ui.shared_components

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.whitedragonvpn.vpn.TunnelManager
import com.example.whitedragonvpn.vpn.TunnelStateProvider
import com.wireguard.android.backend.Tunnel

class BaseViewModel(
    private val tunnelManager: TunnelManager,
    private val stateProvider: TunnelStateProvider
) : ViewModel() {
    fun getCurrentTunnelState(): LiveData<Tunnel.State> {
        return stateProvider.getStateObservable()
    }

    fun getCurrentCountryCode(): LiveData<String> {
        return stateProvider.getCurrentCountryObservable()
    }
}