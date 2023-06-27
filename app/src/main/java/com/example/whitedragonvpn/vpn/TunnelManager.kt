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
    private val configRepository: ConfigRepository
) : TunnelLauncher {
    private val tunnel = WgTunnel()

    override suspend fun toggleTunnelState(): Unit =
        withContext(Dispatchers.IO) {
            var config: Config? = null
            val newState = when (tunnel.state.value) {
                Tunnel.State.DOWN -> {
                    config = configRepository.getConfig()
                    Tunnel.State.UP
                }

                else -> {
                    config = null
                    Tunnel.State.DOWN
                }
            }
            try {
                backend.setState(
                    tunnel,
                    newState,
                    config
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    fun getTunnelStateObservable(): LiveData<Tunnel.State> {
        return tunnel.state
    }
}