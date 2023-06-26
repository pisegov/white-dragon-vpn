package com.example.whitedragonvpn

import android.app.Application
import android.content.Context
import com.example.whitedragonvpn.ioc.ApplicationComponent
import com.wireguard.android.backend.GoBackend

class App : Application() {
    val applicationComponent by lazy {
        val backend = GoBackend(applicationContext)
        ApplicationComponent(backend)
    }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}
