package com.example.whitedragonvpn.ui.base_fragment

import android.app.Activity
import android.graphics.Color
import androidx.lifecycle.LifecycleOwner
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.wireguard.android.backend.Tunnel

class BaseFragmentController(
    private val activity: Activity,
    private val viewBinding: FragmentBaseBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: BaseViewModel
) {
    private val connectionSwitch: VpnConnectionSwitch = activity as VpnConnectionSwitch
    fun setupViews() {
        viewBinding.buttonFirst.setOnClickListener {
            connectionSwitch.onSwitchClicked()
        }

        viewModel.getCurrentTunnelState().observe(lifecycleOwner) { state ->
            val colorsMap: Map<Tunnel.State, Int> = mapOf(
                Tunnel.State.UP to Color.CYAN,
                Tunnel.State.DOWN to Color.parseColor("#D0BCFF")
            )

            colorsMap[state]?.let {
                viewBinding.buttonFirst.setBackgroundColor(it)
            }
        }
    }
}
