package com.example.whitedragonvpn.ioc

import androidx.fragment.app.Fragment
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel

class BaseFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel: BaseViewModel
)