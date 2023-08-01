package com.example.whitedragonvpn.ui.base_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.example.whitedragonvpn.ui.base_fragment.ioc.BaseFragmentComponent
import com.example.whitedragonvpn.ui.base_fragment.ioc.BaseFragmentViewComponent
import com.example.whitedragonvpn.ui.base_fragment.ioc.DaggerBaseFragmentComponent
import com.example.whitedragonvpn.ui.base_fragment.ioc.DaggerBaseFragmentViewComponent
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import com.example.whitedragonvpn.ui.viewBinding

class BaseFragment : Fragment(R.layout.fragment_base) {

    private val binding by viewBinding { FragmentBaseBinding.bind(it) }

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: BaseFragmentComponent
    private var fragmentViewComponent: BaseFragmentViewComponent? = null
    private val viewModel: BaseViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = DaggerBaseFragmentComponent.factory()
            .create(applicationComponent = applicationComponent, fragment = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentViewComponent = DaggerBaseFragmentViewComponent.factory()
            .create(fragmentComponent, binding, viewLifecycleOwner, viewModel)
            .apply {
                baseFragmentViewController.setupViews()
            }
    }
}