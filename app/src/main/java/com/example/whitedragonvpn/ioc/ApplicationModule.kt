package com.example.whitedragonvpn.ioc

import android.content.Context
import com.example.whitedragonvpn.data.settings.store.SettingsRepository
import com.example.whitedragonvpn.utils.datastore
import com.example.whitedragonvpn.vpn.TunnelLauncher
import com.example.whitedragonvpn.vpn.TunnelManager
import com.wireguard.android.backend.Backend
import com.wireguard.android.backend.GoBackend
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ApplicationModule {
    @Binds
    @ApplicationScope
    fun tunnelLauncher(impl: TunnelManager): TunnelLauncher

    companion object {
        @Provides
        fun settingsRepository(applicationContext: Context): SettingsRepository {
            return SettingsRepository(applicationContext.datastore)
        }

        @Provides
        @ApplicationScope
        fun wgBackend(applicationContext: Context): Backend {
            return GoBackend(applicationContext)
        }
    }
}