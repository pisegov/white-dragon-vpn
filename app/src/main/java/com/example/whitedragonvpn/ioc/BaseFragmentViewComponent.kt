package com.example.whitedragonvpn.ioc

import android.view.View

class BaseFragmentViewComponent(
    fragmentComponent: BaseFragmentComponent,
    root: View,
) {

    val baseViewController = BaseFragmentController(
        fragmentComponent.fragment.requireActivity(),
        rootView = root
    )
}