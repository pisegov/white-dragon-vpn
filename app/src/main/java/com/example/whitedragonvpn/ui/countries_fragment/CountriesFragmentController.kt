package com.example.whitedragonvpn.ui.countries_fragment

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whitedragonvpn.databinding.FragmentCountriesBinding
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem
import com.example.whitedragonvpn.ui.countries_fragment.recycler.CountriesAdapter
import com.example.whitedragonvpn.ui.countries_fragment.recycler.CountriesOffsetItemDecoration
import com.example.whitedragonvpn.ui.countries_fragment.recycler.CountriesViewHolder
import com.example.whitedragonvpn.ui.shared_components.VpnConnectionSwitch
import com.example.whitedragonvpn.utils.px
import com.wireguard.android.backend.Tunnel

class CountriesFragmentController(
    private val activity: Activity,
    private val viewBinding: FragmentCountriesBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BaseViewModel
) {

    private val connectionSwitch: VpnConnectionSwitch = activity as VpnConnectionSwitch
    lateinit var countriesRecyclerView: RecyclerView
    lateinit var countriesAdapter: ListAdapter<CountryItem, CountriesViewHolder>
    var tunnelIsUp: Boolean = false
    val countriesList = listOf(
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
        viewModel.getCurrentTunnelState().observe(lifecycleOwner) { state ->
            tunnelIsUp = state == Tunnel.State.UP
        }
        viewModel.getCurrentCountryCode().observe(lifecycleOwner) { countryCode ->
            val newList = countriesList.map { country ->
                country.copy(
                    isChecked =
                    country.code == countryCode && tunnelIsUp
                )
            }
            countriesAdapter.submitList(newList)
        }
    }

    private fun setupRecycler() {
        countriesRecyclerView = viewBinding.rwCountriesRecycler

        val switchListener: (item: CountryItem, state: Boolean) -> Unit = { item, state ->
            val stateMap = mapOf<Boolean, Tunnel.State>(
                true to Tunnel.State.UP,
                false to Tunnel.State.DOWN
            )
            val newList = countriesList.map { country ->
                country.copy(
                    isChecked =
                    country.code == item.code
                )
            }
            countriesAdapter.submitList(newList)
            connectionSwitch.onSwitchClicked(stateMap[state]!!, countryCode = item.code)
        }

        countriesAdapter = CountriesAdapter(switchListener)

        countriesRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(CountriesOffsetItemDecoration(bottomOffset = 10.px))
        }
        countriesAdapter.submitList(
            countriesList
        )
    }
}
