package com.example.whitedragonvpn.ui.countries_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.databinding.FragmentCountriesBinding
import com.example.whitedragonvpn.ui.countries_fragment.ioc.CountriesFragmentComponent
import com.example.whitedragonvpn.ui.countries_fragment.ioc.CountriesFragmentViewComponent
import com.example.whitedragonvpn.ui.countries_fragment.ioc.DaggerCountriesFragmentComponent
import com.example.whitedragonvpn.ui.countries_fragment.ioc.DaggerCountriesFragmentViewComponent
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null

    private val binding get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)

        fragmentViewComponent =
            DaggerCountriesFragmentViewComponent.factory().create(
                fragmentComponent, binding, viewLifecycleOwner
            ).apply {
                countriesViewController.setupViews()
            }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}