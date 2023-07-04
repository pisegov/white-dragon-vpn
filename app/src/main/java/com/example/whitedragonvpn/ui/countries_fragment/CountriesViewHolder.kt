package com.example.whitedragonvpn.ui.countries_fragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem
import com.google.android.material.materialswitch.MaterialSwitch

class CountriesViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val countryTitle: TextView = itemView.findViewById(R.id.tw_country_title)
    private val countrySwitch: MaterialSwitch = itemView.findViewById(R.id.switch_country_checked)

    fun onBind(countryItem: CountryItem) {
        countryTitle.text = countryItem.title
        countrySwitch.isChecked = countryItem.isChecked
    }
}