package com.example.whitedragonvpn.ui.countries_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whitedragonvpn.databinding.FragmentCountriesBinding
import com.example.whitedragonvpn.ui.countries_fragment.model.CountryItem
import com.example.whitedragonvpn.utils.px

/**
 * A simple [Fragment] subclass as the Countries destination in the navigation.
 */
class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var countriesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesRecyclerView = binding.rwCountriesRecycler
        val countriesAdapter = CountriesAdapter()

        countriesRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(CountriesOffsetItemDecoration(bottomOffset = 12.px))
        }
        countriesAdapter.countriesList = listOf(
            CountryItem("Netherlands", true),
            CountryItem("Russia", false),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}