package com.example.whitedragonvpn.ui.countries_fragment.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.whitedragonvpn.databinding.ItemCountryBinding
import com.example.whitedragonvpn.ui.countries_fragment.ioc.CountriesFragmentScope
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import javax.inject.Inject

@CountriesFragmentScope
class CountriesAdapter @Inject constructor(private val viewModel: BaseViewModel) :
    ListAdapter<CountryItem, CountriesViewHolder>(CountriesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), viewModel
        )
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: CountriesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.bindSwitchState(getItem(position).isChecked)
            }
        }
    }

}