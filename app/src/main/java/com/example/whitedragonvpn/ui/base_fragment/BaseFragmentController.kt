package com.example.whitedragonvpn.ui.base_fragment

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.example.whitedragonvpn.ui.base_fragment.model.SwitchButtonState
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseFragmentController @Inject constructor(
    private val activity: Activity,
    private val viewBinding: FragmentBaseBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BaseViewModel
) {
    private val switchButtonStateAdapterMap: Map<Tunnel.State, SwitchButtonState> = mapOf(
        Tunnel.State.UP to SwitchButtonState(
            color = R.color.switch_button_connected,
            stringId = R.string.connected
        ),
        Tunnel.State.DOWN to SwitchButtonState(
            R.color.primary,
            R.string.connect
        ),
    )

    fun setupViews() {

        val switchButton = viewBinding.buttonFirst
        switchButton.setOnClickListener {
            viewModel.toggleTunnelState()
        }

        viewBinding.btnNextFragment.setOnClickListener {
            findNavController(viewBinding.root).navigate(R.id.action_BaseFragment_to_CountriesFragment)
        }
        lifecycleOwner.lifecycleScope.launch {
            viewModel.getCurrentTunnelState().collect { state ->
                switchButtonStateAdapterMap[state]?.let { buttonState ->
                    switchButton.apply {
                        setBackgroundColor(resources.getColor(buttonState.color, activity.theme))
                        setText(buttonState.stringId)
                    }
                }
            }
        }
    }
}
