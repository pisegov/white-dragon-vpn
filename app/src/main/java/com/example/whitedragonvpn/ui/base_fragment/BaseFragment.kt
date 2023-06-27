package com.example.whitedragonvpn.ui.base_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.example.whitedragonvpn.ioc.BaseFragmentComponent
import com.example.whitedragonvpn.ioc.BaseFragmentViewComponent

class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null

    private val binding get() = _binding!!

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: BaseFragmentComponent
    private var fragmentViewComponent: BaseFragmentViewComponent? = null
    private val viewModel: BaseViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = BaseFragmentComponent(
            applicationComponent,
            this,
            viewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBaseBinding.inflate(inflater, container, false)

        fragmentViewComponent =
            BaseFragmentViewComponent(fragmentComponent, binding, viewLifecycleOwner)
                .apply {
                    baseViewController.setupViews()
                }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}