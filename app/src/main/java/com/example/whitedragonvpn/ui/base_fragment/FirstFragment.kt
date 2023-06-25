package com.example.whitedragonvpn.ui.base_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.whitedragonvpn.App
import com.example.whitedragonvpn.databinding.FragmentFirstBinding
import com.example.whitedragonvpn.ioc.BaseFragmentComponent
import com.example.whitedragonvpn.ioc.BaseFragmentViewComponent

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: BaseFragmentComponent
    private var fragmentViewComponent: BaseFragmentViewComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = BaseFragmentComponent(
            applicationComponent,
            this,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        fragmentViewComponent = BaseFragmentViewComponent(fragmentComponent, binding.root)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            fragmentViewComponent?.baseViewController?.connectToVpn()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}