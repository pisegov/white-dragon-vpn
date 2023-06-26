package com.example.whitedragonvpn.data

import com.example.whitedragonvpn.BuildConfig
import com.wireguard.config.Config
import com.wireguard.config.InetEndpoint
import com.wireguard.config.InetNetwork
import com.wireguard.config.Interface
import com.wireguard.config.Peer

class ConfigRepository {
    fun getConfig(): Config {
        val interfaceBuilder = Interface.Builder()
        val peerBuilder = Peer.Builder()
        return Config.Builder()
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
    }
}
