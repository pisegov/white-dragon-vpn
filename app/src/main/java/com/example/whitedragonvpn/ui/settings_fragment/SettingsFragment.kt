package com.example.whitedragonvpn.ui.settings_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.databinding.FragmentSettingsBinding
import com.example.whitedragonvpn.ioc.ApplicationComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.DaggerSettingsFragmentComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.DaggerSettingsFragmentViewComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsFragmentComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsFragmentViewComponent
import com.example.whitedragonvpn.ui.settings_fragment.ioc.SettingsViewModel
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val applicationComponent: ApplicationComponent
        get() = App.get(requireContext()).applicationComponent

    private lateinit var fragmentComponent: SettingsFragmentComponent
    private lateinit var fragmentViewComponent: SettingsFragmentViewComponent

    private val viewModel by viewModels<SettingsViewModel> { applicationComponent.viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        fragmentViewComponent = DaggerSettingsFragmentViewComponent.factory().create(
            fragmentComponent,
            binding,
            viewLifecycleOwner
        ).apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewController.setupViews()
            }
        }

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}