package com.example.whitedragonvpn.ioc

import android.app.Activity
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.example.whitedragonvpn.ui.base_fragment.VpnConnectionSwitch

class BaseFragmentController(
    activity: Activity,
    val viewBinding: FragmentBaseBinding,
) {
    private val connectionSwitch: VpnConnectionSwitch = activity as VpnConnectionSwitch
    fun setupViews() {
        viewBinding.buttonFirst.setOnClickListener {
            connectionSwitch.onSwitchClicked()
        }
    }
}
