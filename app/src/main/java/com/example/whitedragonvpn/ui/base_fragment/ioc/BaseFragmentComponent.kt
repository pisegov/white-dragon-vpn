package com.example.whitedragonvpn.ui.base_fragment.ioc

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [BaseFragmentModule::class])
@BaseFragmentScope
interface BaseFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
        ): BaseFragmentComponent
    }

    fun fragment(): Fragment
}