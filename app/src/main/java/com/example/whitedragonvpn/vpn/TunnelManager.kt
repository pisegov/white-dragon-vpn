package com.example.whitedragonvpn.vpn

import com.example.whitedragonvpn.data.ConfigRepository
import com.wireguard.android.backend.Backend
import com.wireguard.android.backend.Tunnel
import com.wireguard.config.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TunnelManager(
    private val backend: Backend,
    configRepository: ConfigRepository
) {
    private val tunnel: Tunnel = WgTunnel()
    private val config: Config = configRepository.getConfig()

    suspend fun setTunnelUp() {
        withContext(Dispatchers.IO) {
            try {
                backend.setState(
                    tunnel,
                    Tunnel.State.UP,
                    config
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}