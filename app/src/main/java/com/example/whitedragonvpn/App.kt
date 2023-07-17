package com.example.whitedragonvpn

import android.app.Application
import android.content.Context
import com.example.whitedragonvpn.ioc.ApplicationScope
import com.example.whitedragonvpn.ioc.DaggerApplicationComponent
import com.example.whitedragonvpn.utils.SystemDialogManager
import com.wireguard.android.backend.GoBackend

@ApplicationScope
class App : Application() {
    val applicationComponent by lazy {
        val backend = GoBackend(applicationContext)
        val systemDialogManager = SystemDialogManager(applicationContext)
        DaggerApplicationComponent.factory()
            .create(wgBackend = backend, dialogManager = systemDialogManager)
    }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}
