package com.example.whitedragonvpn.utils

import android.content.Context
import android.content.Intent
import com.example.whitedragonvpn.ioc.ApplicationScope
import com.wireguard.android.backend.GoBackend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@ApplicationScope
class SystemDialogManager(private val applicationContext: Context) {
    private val _firstConnectionDialog = MutableStateFlow<Intent?>(null)
    val firstConnectionDialog: StateFlow<Intent?> = _firstConnectionDialog

    fun makeFirstConnectionDialog() {
        val intentPrepare: Intent? = GoBackend.VpnService.prepare(applicationContext)

        _firstConnectionDialog.update { intentPrepare }
    }
}