package com.example.whitedragonvpn.ui.countries_fragment.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.whitedragonvpn.databinding.ItemCountryBinding
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel

class CountriesViewHolder(
    private val binding: ItemCountryBinding,
    private val viewModel: BaseViewModel
) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentCountry: CountryItem

    init {
        binding.switchCountryChecked.setOnCheckedChangeListener { switch, state ->
            if (switch.isPressed) {
                viewModel.switchTunnelState(switch, currentCountry, state)
            }
        }
    }

    fun onBind(
        countryItem: CountryItem
    ) {
        this.currentCountry = countryItem
        binding.twCountryTitle.text = countryItem.title
        binding.switchCountryChecked.isChecked = countryItem.isChecked
    }

    fun bindSwitchState(state: Boolean) {
        binding.switchCountryChecked.isChecked = state
    }
}