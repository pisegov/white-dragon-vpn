package com.example.whitedragonvpn.ui.base_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.whitedragonvpn.vpn.TunnelManager
import com.wireguard.android.backend.Tunnel

class BaseViewModel(private val tunnelManager: TunnelManager) : ViewModel() {

    fun getCurrentTunnelState(): LiveData<Tunnel.State> {
        return tunnelManager.getTunnelStateObservable()
    }
}