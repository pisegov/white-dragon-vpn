package com.example.whitedragonvpn.vpn

import com.example.whitedragonvpn.data.config.ConfigRepository
import com.example.whitedragonvpn.data.config.ConfigModel
import com.example.whitedragonvpn.data.remote.retrofit.RetrofitModule
import com.example.whitedragonvpn.ioc.ApplicationScope
import com.example.whitedragonvpn.utils.SystemDialogManager
import com.wireguard.android.backend.Backend
import com.wireguard.android.backend.Tunnel
import com.wireguard.config.Config
import com.wireguard.config.InetEndpoint
import com.wireguard.config.InetNetwork
import com.wireguard.config.Interface
import com.wireguard.config.Peer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ApplicationScope
class TunnelManager @Inject constructor(
    private val backend: Backend,
    private val configRepository: ConfigRepository,
    private val tunnel: WgTunnel,
    private val dialogManager: SystemDialogManager,
) : TunnelLauncher {

    override suspend fun toggleTunnelState(): Unit =
        withContext(Dispatchers.IO) {
            val countryCode = tunnel.currentCountryObservable.value
            val newState = when (tunnel.state.value) {
                Tunnel.State.DOWN -> Tunnel.State.UP
                else -> Tunnel.State.DOWN
            }
            switchTunnelState(newState, countryCode)
        }

    override suspend fun switchTunnelState(state: Tunnel.State, countryCode: String) =
        withContext(Dispatchers.IO) {
            var config: Config? = null
            var newState: Tunnel.State = Tunnel.State.DOWN
            if (state == Tunnel.State.UP) {
                val model = configRepository.getConfig(countryCode)
                model?.let {
                    config = buildConfigFromModel(model)
                    newState = state
                }
            }
            setState(newState, config, countryCode)
        }

    @OptIn(ExperimentalSerializationApi::class)
    private fun setState(state: Tunnel.State, config: Config?, countryCode: String) {
        dialogManager.makeFirstConnectionDialog()
        try {
            backend.setState(
                tunnel,
                state,
                config
            )
            RetrofitModule.clearConnectionPool()
            config?.let {
                tunnel.updateCountryCode(countryCode)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun buildConfigFromModel(configModel: ConfigModel?): Config? {
        if (configModel == null) return null

        val interfaceBuilder = Interface.Builder()
        val peerBuilder = Peer.Builder()
        return Config.Builder()
            .setInterface(
                interfaceBuilder.addAddress(InetNetwork.parse(configModel.address))
                    .parsePrivateKey(configModel.peerPrivateKey)
                    .build()
            )
            .addPeer(
                peerBuilder.addAllowedIp(InetNetwork.parse(configModel.allowedIps))
                    .setEndpoint(
                        InetEndpoint.parse(configModel.endpoint)
                    ).parsePublicKey(configModel.serverPublicKey)
                    .setPersistentKeepalive(20)
                    .build()
            )
            .build()
    }
}