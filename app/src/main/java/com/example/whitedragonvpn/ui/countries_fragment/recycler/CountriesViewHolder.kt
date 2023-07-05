package com.example.whitedragonvpn.ui.countries_fragment.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.whitedragonvpn.databinding.ItemCountryBinding
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem

class CountriesViewHolder(
    private val binding: ItemCountryBinding,
    private val switchListener: (item: CountryItem, state: Boolean) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentCountry: CountryItem

    init {
        binding.switchCountryChecked.setOnCheckedChangeListener { switch, state ->
            switchListener(currentCountry, state)
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