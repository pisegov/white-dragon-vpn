package com.example.whitedragonvpn.ioc

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.whitedragonvpn.utils.SystemDialogManager
import com.example.whitedragonvpn.vpn.TunnelLauncher
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ApplicationModule::class, ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): ApplicationComponent
    }

    val tunnelLauncher: TunnelLauncher

    val dialogManager: SystemDialogManager

    val viewModelFactory: ViewModelProvider.Factory
}