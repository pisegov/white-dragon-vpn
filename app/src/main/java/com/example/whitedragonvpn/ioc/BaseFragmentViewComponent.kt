package com.example.whitedragonvpn.ioc

import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.example.whitedragonvpn.ui.base_fragment.BaseFragmentController

class BaseFragmentViewComponent(
    fragmentComponent: BaseFragmentComponent,
    viewBinding: ViewBinding,
    lifecycleOwner: LifecycleOwner
) {

    val baseViewController = BaseFragmentController(
        fragmentComponent.fragment.requireActivity(),
        viewBinding = viewBinding as FragmentBaseBinding,
        lifecycleOwner,
        viewModel = fragmentComponent.viewModel
    )
}