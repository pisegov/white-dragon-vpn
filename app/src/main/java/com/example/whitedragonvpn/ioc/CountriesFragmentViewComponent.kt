package com.example.whitedragonvpn.ioc

import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.whitedragonvpn.databinding.FragmentCountriesBinding
import com.example.whitedragonvpn.ui.countries_fragment.CountriesFragmentController

class CountriesFragmentViewComponent(
    fragmentComponent: CountriesFragmentComponent,
    viewBinding: ViewBinding,
    lifecycleOwner: LifecycleOwner
) {

    val countriesViewController = CountriesFragmentController(
        fragmentComponent.fragment.requireActivity(),
        viewBinding = viewBinding as FragmentCountriesBinding,
        lifecycleOwner,
//        viewModel = fragmentComponent.viewModel
    )
}