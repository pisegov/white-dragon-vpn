package com.example.whitedragonvpn.ui.countries_fragment

import android.app.Activity
import android.widget.CompoundButton
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class CountriesFragmentController @Inject constructor(
    private val activity: Activity,
    private val viewBinding: FragmentCountriesBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BaseViewModel
) {

    private val connectionSwitch: VpnConnectionSwitch = activity as VpnConnectionSwitch
    private lateinit var countriesRecyclerView: RecyclerView
    private lateinit var countriesAdapter: ListAdapter<CountryItem, CountriesViewHolder>
    private val stateUpdateMutex = Mutex()
    private var tunnelIsUp = AtomicBoolean(false)
    private lateinit var currentCountry: String

    private val countriesList = listOf(
        CountryItem("Netherlands", "ne", false),
        CountryItem("Russia", "ru", false),
        CountryItem("USA", "us", false),
        CountryItem("Georgia", "ge", false),
    )
    private val stateMap = mapOf<Boolean, Tunnel.State>(
        true to Tunnel.State.UP,
        false to Tunnel.State.DOWN
    )

    fun setupViews() {
        setupRecycler()
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.getCurrentCountryCode().collect { countryCode ->
                stateUpdateMutex.withLock { currentCountry = countryCode }
                updateCountriesList()
            }
        }
        lifecycleOwner.lifecycleScope.launch {
            viewModel.getCurrentTunnelState().collect { state ->
                tunnelIsUp.getAndSet(state == Tunnel.State.UP)
                updateCountriesList()
            }
        }
    }

    private fun setupRecycler() {
        countriesRecyclerView = viewBinding.rwCountriesRecycler

        val switchListener: (switch: CompoundButton, item: CountryItem, state: Boolean) -> Unit =
            { switch, item, state ->
                switch.isChecked = false
                connectionSwitch.onSwitchClicked(state = stateMap[state]!!, countryCode = item.code)
            }

        countriesAdapter = CountriesAdapter(switchListener)

        countriesRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(CountriesOffsetItemDecoration(bottomOffset = 10.px))
        }
    }

    private fun updateCountriesList() {
        val newList = countriesList.map { countryItem ->
            countryItem.copy(
                isChecked =
                (countryItem.code == currentCountry) && tunnelIsUp.get()
            )
        }
        countriesAdapter.submitList(newList)
    }
}
