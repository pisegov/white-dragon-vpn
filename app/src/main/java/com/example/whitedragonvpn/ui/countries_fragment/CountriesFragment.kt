package com.example.whitedragonvpn.ui.countries_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.databinding.FragmentCountriesBinding
import com.example.whitedragonvpn.ui.countries_fragment.ioc.CountriesFragmentComponent
import com.example.whitedragonvpn.ui.countries_fragment.ioc.CountriesFragmentViewComponent
import com.example.whitedragonvpn.ui.countries_fragment.ioc.DaggerCountriesFragmentComponent
import com.example.whitedragonvpn.ui.countries_fragment.ioc.DaggerCountriesFragmentViewComponent
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import com.example.whitedragonvpn.ui.viewBinding

class CountriesFragment : Fragment(R.layout.fragment_countries) {

    private val binding by viewBinding { FragmentCountriesBinding.bind(it) }

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: CountriesFragmentComponent
    private var fragmentViewComponent: CountriesFragmentViewComponent? = null
    private val viewModel: BaseViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = DaggerCountriesFragmentComponent.factory()
            .create(applicationComponent, this, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentViewComponent =
            DaggerCountriesFragmentViewComponent.factory().create(
                fragmentComponent, binding, viewLifecycleOwner
            ).apply {
                countriesViewController.setupViews()
            }
    }
}