package com.example.whitedragonvpn.vpn

import com.wireguard.android.backend.Tunnel

class WgTunnel : Tunnel {
    override fun getName(): String {
        return "wgpreconf"
    }

    override fun onStateChange(newState: Tunnel.State) {
    }
}