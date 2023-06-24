package com.example.whitedragonvpn.ioc

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.whitedragonvpn.BuildConfig
import com.example.whitedragonvpn.vpn.WgTunnel
import com.wireguard.android.backend.GoBackend
import com.wireguard.android.backend.Tunnel
import com.wireguard.config.Config
import com.wireguard.config.InetEndpoint
import com.wireguard.config.InetNetwork
import com.wireguard.config.Interface
import com.wireguard.config.Peer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseFragmentController(
    private val activity: Activity,
    rootView: View,
) {
    fun connectToVpn() {
        val tunnel = WgTunnel()
        val intentPrepare: Intent? = GoBackend.VpnService.prepare(activity)
        if (intentPrepare != null) {
            startActivityForResult(activity, intentPrepare, 0, null)
        }
        val interfaceBuilder = Interface.Builder()
        val peerBuilder = Peer.Builder()
        val backend = GoBackend(activity)

        val coroutineScope = CoroutineScope(Dispatchers.Default)
        coroutineScope.launch {
            try {
                backend.setState(
                    tunnel,
                    Tunnel.State.UP,
                    Config.Builder()
                        .setInterface(
                            interfaceBuilder.addAddress(InetNetwork.parse(BuildConfig.HARDCODED_ADDRESS))
                                .parsePrivateKey(BuildConfig.HARDCODED_PRIVATE_KEY)
                                .build()
                        )
                        .addPeer(
                            peerBuilder.addAllowedIp(InetNetwork.parse(BuildConfig.HARDCODED_ALOWED_IP))
                                .setEndpoint(
                                    InetEndpoint.parse(BuildConfig.HARDCODED_ENDPOINT)
                                ).parsePublicKey(BuildConfig.HARDCODED_PUBLIC_KEY)
                                .setPersistentKeepalive(20)
                                .build()
                        )
                        .build()
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
