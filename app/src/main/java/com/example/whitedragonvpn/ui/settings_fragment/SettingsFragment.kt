package com.example.whitedragonvpn.ui.settings_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceFragmentCompat
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.ioc.ApplicationComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.DaggerSettingsFragmentComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.DaggerSettingsFragmentViewComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsFragmentComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsFragmentViewComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsViewModel
import kotlinx.coroutines.launch

class SettingsFragment : PreferenceFragmentCompat() {

    private val applicationComponent: ApplicationComponent
        get() = App.get(requireContext()).applicationComponent

    private lateinit var fragmentComponent: SettingsFragmentComponent
    private lateinit var fragmentViewComponent: SettingsFragmentViewComponent

    private val viewModel by viewModels<SettingsViewModel> { applicationComponent.viewModelFactory }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        fragmentComponent = DaggerSettingsFragmentComponent.factory().create(
            applicationComponent,
            this,
            viewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentViewComponent = DaggerSettingsFragmentViewComponent.factory().create(
            fragmentComponent,
            preferenceManager,
            viewLifecycleOwner
        ).apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewController.setupPreferences()
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}