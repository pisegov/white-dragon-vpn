package com.example.whitedragonvpn

import android.app.Application
import android.content.Context
import com.example.whitedragonvpn.ioc.ApplicationScope
import com.example.whitedragonvpn.ioc.DaggerApplicationComponent

@ApplicationScope
class App : Application() {
    val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}
