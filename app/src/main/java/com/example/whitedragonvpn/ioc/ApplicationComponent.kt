package com.example.whitedragonvpn.ioc

import com.example.whitedragonvpn.data.ConfigRepository
import com.example.whitedragonvpn.data.remote.retrofit.NetworkConfigSource
import com.example.whitedragonvpn.vpn.TunnelLauncher
import com.example.whitedragonvpn.vpn.TunnelManager
import com.example.whitedragonvpn.vpn.TunnelStateProvider
import com.example.whitedragonvpn.vpn.WgTunnel
import com.wireguard.android.backend.Backend
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class ApplicationComponent(wgBackend: Backend) {
    private val networkConfigSource = NetworkConfigSource()
    private val configRepository = ConfigRepository(networkConfigSource)
    private val tunnel = WgTunnel()
    private val tunnelStateProvider = TunnelStateProvider(tunnel)
    private val tunnelManager = TunnelManager(wgBackend, configRepository, tunnel)

    val tunnelLauncher: TunnelLauncher = tunnelManager
    val viewModelFactory = ViewModelFactory(tunnelManager, tunnelStateProvider)
}
