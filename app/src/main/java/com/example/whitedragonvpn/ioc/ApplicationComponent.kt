package com.example.whitedragonvpn.ioc

import com.example.whitedragonvpn.data.ConfigRepository
import com.example.whitedragonvpn.vpn.TunnelManager
import com.wireguard.android.backend.Backend

class ApplicationComponent(wgBackend: Backend) {
    private val configRepository = ConfigRepository()
    val tunnelManager = TunnelManager(wgBackend, configRepository)
}
