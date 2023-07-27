package com.example.whitedragonvpn.ui.countries_fragment

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whitedragonvpn.databinding.FragmentCountriesBinding
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem
import com.example.whitedragonvpn.ui.countries_fragment.recycler.CountriesAdapter
import com.example.whitedragonvpn.ui.countries_fragment.recycler.CountriesOffsetItemDecoration
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import com.example.whitedragonvpn.utils.px
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesFragmentController @Inject constructor(
    private val activity: Activity,
    private val viewBinding: FragmentCountriesBinding,
    private val countriesAdapter: CountriesAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BaseViewModel
) {

    private lateinit var countriesRecyclerView: RecyclerView

    private val countriesList = listOf(
        CountryItem("Netherlands", "ne", false),
        CountryItem("Russia", "ru", false),
        CountryItem("USA", "us", false),
        CountryItem("Georgia", "ge", false),
    )

    fun setupViews() {
        setupRecycler()
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.getConnectionState().collect { connectionState ->
                updateCountriesList(
                    connectionState.state == Tunnel.State.UP,
                    connectionState.countryCode
                )
            }
        }
    }

    private fun setupRecycler() {
        countriesRecyclerView = viewBinding.rwCountriesRecycler

        countriesRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(CountriesOffsetItemDecoration(bottomOffset = 10.px))
        }
    }

    private fun updateCountriesList(tunnelIsUp: Boolean, currentCountry: String) {
        val newList = countriesList.map { countryItem ->
            countryItem.copy(
                isChecked =
                (countryItem.code == currentCountry) && tunnelIsUp
            )
        }
        countriesAdapter.submitList(newList)
    }
}
