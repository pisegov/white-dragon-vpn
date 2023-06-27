package com.example.whitedragonvpn.vpn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wireguard.android.backend.Tunnel

class WgTunnel : Tunnel {
    private val _state = MutableLiveData(Tunnel.State.DOWN)
    val state: LiveData<Tunnel.State> = _state

    override fun getName(): String {
        return "wgpreconf"
    }

    override fun onStateChange(newState: Tunnel.State) {
        _state.postValue(newState)
    }
}