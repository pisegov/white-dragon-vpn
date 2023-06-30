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
            val config: Config?
            val newState = when (tunnel.state.value) {
                Tunnel.State.DOWN -> {
                    config = configRepository.getConfig()
                    config?.let {
                        Tunnel.State.UP
                    } ?: Tunnel.State.DOWN
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