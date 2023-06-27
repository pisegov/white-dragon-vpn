package com.example.whitedragonvpn.vpn

import androidx.lifecycle.LiveData
import com.example.whitedragonvpn.data.ConfigRepository
import com.wireguard.android.backend.Backend
import com.wireguard.android.backend.Tunnel
import com.wireguard.config.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TunnelManager(
    private val backend: Backend,
    configRepository: ConfigRepository
) : TunnelLauncher {
    private val tunnel = WgTunnel()
    private val config: Config = configRepository.getConfig()

    override suspend fun setTunnelUp() {
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

    fun getTunnelStateObservable(): LiveData<Tunnel.State> {
        return tunnel.state
    }
}