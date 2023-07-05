package com.example.whitedragonvpn.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import com.example.whitedragonvpn.vpn.TunnelManager

class ViewModelFactory(private val tunnelManager: TunnelManager) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            BaseViewModel::class.java -> BaseViewModel(tunnelManager)
            else -> {
                throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
            }
        } as T
    }
}