package com.example.whitedragonvpn.ui.settings_fragment

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.whitedragonvpn.databinding.FragmentSettingsBinding
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsFragmentViewScope
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SettingsFragmentViewScope
class SettingsFragmentViewController @Inject constructor(
    private val activity: Activity,
    private val viewBinding: FragmentSettingsBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: SettingsViewModel
) {

    fun setupViews() {
        viewBinding.settings.text = "Settings"

        lifecycleOwner.lifecycleScope.launch {
            collectSettings()
        }
    }

    private suspend fun collectSettings() {
        val flow = viewModel.getSettingsFlow()
        flow.collect { settings ->
            val protocol = settings.protocol
            val useHomeCountry = settings.useHomeCountry
            val applicationLanguage = settings.applicationLanguage
        }
    }
}