package com.example.whitedragonvpn.ui.shared_components

import com.wireguard.android.backend.Tunnel

interface VpnConnectionSwitch {
    fun onSwitchClicked()
    fun onSwitchClicked(state: Tunnel.State, countryCode: String)
}