package com.example.whitedragonvpn.ui.countries_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem

class CountriesAdapter : RecyclerView.Adapter<CountriesViewHolder>() {
    var countriesList = listOf<CountryItem>()
        set(value) {
            val callback = CountriesDiffCallback<CountryItem>(
                oldList = field,
                newList = value,
                areItemsTheSameImpl = { oldItem, newItem -> oldItem.title == newItem.title }
            )

            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountriesViewHolder(
            layoutInflater.inflate(
                R.layout.item_country,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.onBind(countriesList[position])
    }

    override fun getItemCount(): Int = countriesList.size
}