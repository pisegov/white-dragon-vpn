package com.example.whitedragonvpn.ui.base_fragment

import android.app.Activity
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
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

        lifecycleOwner.lifecycleScope.launch {
            viewModel.getConnectionState().collect { connectionState ->
                switchButtonStateAdapterMap[connectionState.state]?.let { buttonState ->
                    switchButton.apply {
                        setBackgroundColor(resources.getColor(buttonState.color, activity.theme))
                        setText(buttonState.stringId)
                    }
                }
                changeConnectionStatus(connectionState.state == Tunnel.State.UP)
            }
        }
    }

    private fun changeConnectionStatus(isConnected: Boolean) {
        val container = viewBinding.connectionStatusContainer
        val connection = viewBinding.connectionStatusString
        val ip = viewBinding.ipStatusString
        val country = viewBinding.countryStatusString

        if (isConnected) {
            container.background =
                AppCompatResources.getDrawable(activity, R.drawable.status_background_connected)
            connection.text =
                activity.getString(
                    R.string.property_value_string,
                    activity.getString(R.string.connection_status),
                    activity.getString(R.string.connected)
                )
            ip.apply {
                visibility = View.VISIBLE
                text = activity.getString(
                    R.string.property_value_string,
                    activity.getString(R.string.ip_address_status),
                    activity.getString(R.string.todo)
                )
            }
            country.apply {
                visibility = View.VISIBLE
                text = activity.getString(
                    R.string.property_value_string,
                    activity.getString(R.string.country_status),
                    activity.getString(R.string.todo)
                )
            }
        } else {
            container.background =
                AppCompatResources.getDrawable(activity, R.drawable.status_background_disconnected)
            connection.text =
                activity.getString(
                    R.string.property_value_string,
                    activity.getString(R.string.connection_status),
                    activity.getString(R.string.disconnected)
                )
            ip.apply {
                visibility = View.GONE
            }
            country.apply {
                visibility = View.GONE
            }
        }
    }
}
