package com.example.whitedragonvpn.utils

import android.content.Context
import android.content.res.Resources
import androidx.datastore.dataStore
import com.example.whitedragonvpn.data.settings.store.SettingsSerializer

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Context.datastore by dataStore(
    fileName = "proto.datastore",
    serializer = SettingsSerializer,
)