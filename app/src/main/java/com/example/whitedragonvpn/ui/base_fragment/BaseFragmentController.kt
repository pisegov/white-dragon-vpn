package com.example.whitedragonvpn.ui.base_fragment

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation.findNavController
import com.example.whitedragonvpn.R
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.example.whitedragonvpn.ui.base_fragment.model.SwitchButtonState
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import com.example.whitedragonvpn.ui.shared_components.VpnConnectionSwitch
import com.wireguard.android.backend.Tunnel
import javax.inject.Inject

class BaseFragmentController @Inject constructor(
    private val activity: Activity,
    private val viewBinding: FragmentBaseBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BaseViewModel
) {
    private val connectionSwitch: VpnConnectionSwitch = activity as VpnConnectionSwitch
    fun setupViews() {

        val switchButton = viewBinding.buttonFirst
        switchButton.setOnClickListener {
            connectionSwitch.onSwitchClicked()
        }

        viewBinding.btnNextFragment.setOnClickListener {
            findNavController(viewBinding.root).navigate(R.id.action_BaseFragment_to_CountriesFragment)
        }
        viewModel.getCurrentTunnelState().observe(lifecycleOwner) { state ->
            val switchButtonStateAdapterMap: Map<Tunnel.State, SwitchButtonState> = mapOf(
                Tunnel.State.UP to SwitchButtonState(
                    color = R.color.switch_button_connected,
                    stringId = R.string.connected
                ),
                Tunnel.State.DOWN to SwitchButtonState(
                    R.color.primary,
                    R.string.connect
                ),
            )

            switchButtonStateAdapterMap[state]?.let { buttonState ->
                switchButton.apply {
                    setBackgroundColor(resources.getColor(buttonState.color, activity.theme))
                    setText(buttonState.stringId)
                }
            }
        }
    }
}
