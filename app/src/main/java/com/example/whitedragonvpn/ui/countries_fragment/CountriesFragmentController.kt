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
import com.example.whitedragonvpn.utils.px

class CountriesFragmentController(
    private val activity: Activity,
    private val viewBinding: FragmentCountriesBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BaseViewModel
) {
    lateinit var countriesRecyclerView: RecyclerView
    lateinit var countriesAdapter: ListAdapter<CountryItem, CountriesViewHolder>

    fun setupViews() {
        setupRecycler()
    }

    private fun setupRecycler() {
        countriesRecyclerView = viewBinding.rwCountriesRecycler
        val countriesList = listOf(
            CountryItem("Netherlands", true),
            CountryItem("Russia", false),
            CountryItem("USA", false),
            CountryItem("Georgia", false),
        )
        countriesAdapter = CountriesAdapter { item, state ->
            when (state) {
                true -> {
                    val listCopy = countriesList.map { listItem ->
                        listItem.copy(isChecked = listItem === item)
                    }
                    countriesAdapter.submitList(listCopy)
                }

                false -> {}
            }
        }

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
