package com.example.whitedragonvpn.ui.shared_components

import android.widget.CompoundButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem
import com.example.whitedragonvpn.vpn.TunnelLauncher
import com.example.whitedragonvpn.vpn.TunnelStateProvider
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseViewModel @Inject constructor(
    private val tunnelLauncher: TunnelLauncher,
    private val stateProvider: TunnelStateProvider
) : ViewModel() {

    private val stateMap = mapOf<Boolean, Tunnel.State>(
        true to Tunnel.State.UP,
        false to Tunnel.State.DOWN
    )

    fun getCurrentTunnelState() = stateProvider.getStateFlow()
    fun getCurrentCountryCode() = stateProvider.getCurrentCountryFlow()

    fun toggleTunnelState() {
        viewModelScope.launch {
            tunnelLauncher.toggleTunnelState()
        }
    }

    fun switchTunnelState(switch: CompoundButton, item: CountryItem, state: Boolean) {
        switch.isChecked = false
        viewModelScope.launch {
            tunnelLauncher.switchTunnelState(state = stateMap[state]!!, countryCode = item.code)
        }
    }
}