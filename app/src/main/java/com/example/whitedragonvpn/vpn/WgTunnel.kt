package com.example.whitedragonvpn.vpn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whitedragonvpn.ioc.ApplicationScope
import com.wireguard.android.backend.Tunnel
import javax.inject.Inject

@ApplicationScope
class WgTunnel @Inject constructor() : Tunnel {
    private val _state = MutableLiveData(Tunnel.State.DOWN)
    val state: LiveData<Tunnel.State> = _state

    private val currentCountry = MutableLiveData<String>("ne")
    val currentCountryObservable: LiveData<String> = currentCountry
    override fun getName(): String {
        return "wgpreconf"
    }

    override fun onStateChange(newState: Tunnel.State) {
        _state.postValue(newState)
    }

    fun updateCountryCode(countryCode: String) {
        currentCountry.postValue(countryCode)
    }
}