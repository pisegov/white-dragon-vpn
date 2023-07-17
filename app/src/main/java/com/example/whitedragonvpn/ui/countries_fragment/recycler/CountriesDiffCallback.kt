package com.example.whitedragonvpn.ui.countries_fragment.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem

object CountriesDiffCallback : DiffUtil.ItemCallback<CountryItem>() {

    override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
        return oldItem.isChecked == newItem.isChecked
    }

    override fun getChangePayload(oldItem: CountryItem, newItem: CountryItem): Any? {
        return if (oldItem.isChecked != newItem.isChecked) true else null
    }
}