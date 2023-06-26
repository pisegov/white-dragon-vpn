package com.example.whitedragonvpn.ioc

import androidx.viewbinding.ViewBinding
import com.example.whitedragonvpn.databinding.FragmentBaseBinding

class BaseFragmentViewComponent(
    fragmentComponent: BaseFragmentComponent,
    viewBinding: ViewBinding,
) {

    val baseViewController = BaseFragmentController(
        fragmentComponent.fragment.requireActivity(),
        viewBinding = viewBinding as FragmentBaseBinding
    )
}