package com.example.whitedragonvpn.ioc

import com.example.whitedragonvpn.vpn.TunnelLauncher
import com.example.whitedragonvpn.vpn.TunnelManager
import dagger.Binds
import dagger.Module

@Module
interface ApplicationModule {
    @Binds
    @ApplicationScope
    fun tunnelLauncher(impl: TunnelManager): TunnelLauncher
}